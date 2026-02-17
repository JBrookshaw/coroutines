package com.coroutines.service

import com.coroutines.client.FbiWantedClient
import com.coroutines.client.WantedResponse
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class FbiWantedService(
        private val fbiWantedClient: FbiWantedClient
) {
    private val log = LoggerFactory.getLogger(FbiWantedService::class.java)

    suspend fun getWantedList(page: Int? = 1): WantedResponse {
        return try {
            fbiWantedClient.getWantedList(page = page)
        } catch (ex: Exception) {
            log.error("Failed to fetch FBI wanted list", ex)
            throw ex
        }
    }

    suspend fun getHighestRewardUrl(page: Int? = 1): String? {

        val response = fbiWantedClient.getWantedList(page = page)

        return response.items
                .mapNotNull { item ->
                    val rewardAmount = extractMaxDollarAmount(item.rewardText)
                    if (rewardAmount != null) {
                        rewardAmount to item.url
                    } else {
                        null
                    }
                }
                .maxByOrNull { it.first }
                ?.second
    }

    private fun extractMaxDollarAmount(text: String?): Long? {
        if (text.isNullOrBlank()) return null

        val regex = Regex("""\$(\d{1,3}(,\d{3})*|\d+)""")

        return regex.findAll(text)
                .mapNotNull { it.groupValues[1].replace(",", "").toLongOrNull() }
                .maxOrNull()
    }
}
