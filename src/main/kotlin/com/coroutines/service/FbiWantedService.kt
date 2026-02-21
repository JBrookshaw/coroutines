package com.coroutines.service

import com.coroutines.client.FbiWantedClient
import com.coroutines.client.WantedResponse
import jakarta.inject.Singleton
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import org.slf4j.LoggerFactory
import kotlin.math.ceil

@Singleton
class FbiWantedService(
    private val fbiWantedClient: FbiWantedClient,
) {
    private val log = LoggerFactory.getLogger(FbiWantedService::class.java)

    suspend fun getWantedList(page: Int? = 1): WantedResponse =
        try {
            fbiWantedClient.getWantedList(page = page)
        } catch (ex: Exception) {
            log.error("Failed to fetch FBI wanted list", ex)
            throw ex
        }

    suspend fun getHighestRewardUrl(page: Int? = 1): String? {
        val response = fbiWantedClient.getWantedList(page = page)

        return response.items
            .mapNotNull { item ->
                extractMaxDollarAmount(item.rewardText)?.let { reward ->
                    reward to item.url
                }
            }
            .maxByOrNull { it.first }
            ?.second
    }

    suspend fun getHighestRewardAcrossAllPages(): String? = coroutineScope {
        val firstPage = fbiWantedClient.getWantedList(page = 1)

        val pageSize = firstPage.items.size.takeIf { it > 0 } ?: 20
        val totalPages = ceil(firstPage.total.toDouble() / pageSize).toInt()
        val semaphore = Semaphore(5)

        val deferredPages = (2..totalPages).map { pageNumber ->
            async {
                semaphore.withPermit {
                    fbiWantedClient.getWantedList(page = pageNumber)
                }
            }
        }

        val allResponses = listOf(firstPage) + deferredPages.awaitAll()

        allResponses
            .mapNotNull { response ->
                response.items.maxByOrNull { extractMaxDollarAmount(it.rewardText) ?: 0L }
            }
            .maxByOrNull { extractMaxDollarAmount(it.rewardText) ?: 0L }
            ?.url
    }

    private fun extractMaxDollarAmount(text: String?): Long? {
        if (text.isNullOrBlank()) return null

        return dollarAmountRegex.findAll(text)
            .mapNotNull { it.groupValues[1].replace(",", "").toLongOrNull() }
            .maxOrNull()
    }

    private companion object {
        val dollarAmountRegex = Regex("""\$(\d{1,3}(,\d{3})*|\d+)""")
    }
}
