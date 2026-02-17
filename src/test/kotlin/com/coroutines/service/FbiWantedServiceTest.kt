package com.coroutines.service

import com.coroutines.client.FbiWantedClient
import com.coroutines.client.WantedItem
import com.coroutines.client.WantedResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FbiWantedServiceTest {

    private lateinit var client: FbiWantedClient
    private lateinit var service: FbiWantedService

    @BeforeEach
    fun setup() {
        client = mockk()
        service = FbiWantedService(client)
    }

    @Test
    fun `getWantedList returns client response`() = runTest {
        val response = WantedResponse(
                total = 1,
                page = 1,
                items = emptyList()
        )

        coEvery { client.getWantedList(1) } returns response

        val result = service.getWantedList(1)

        assertEquals(response, result)
        coVerify { client.getWantedList(1) }
    }

    @Test
    fun `getHighestRewardUrl returns highest reward url`() = runTest {

        val response = WantedResponse(
                total = 3,
                page = 1,
                items = listOf(
                        WantedItem(rewardText = "Up to $5,000", url = "low"),
                        WantedItem(rewardText = "Up to $250,000", url = "high"),
                        WantedItem(rewardText = "Up to $25,000", url = "mid")
                )
        )

        coEvery { client.getWantedList(1) } returns response

        val result = service.getHighestRewardUrl(1)

        assertEquals("high", result)
    }

    @Test
    fun `getHighestRewardUrl returns null when no rewards`() = runTest {

        val response = WantedResponse(
                total = 2,
                page = 1,
                items = listOf(
                        WantedItem(rewardText = null, url = "a"),
                        WantedItem(rewardText = "", url = "b")
                )
        )

        coEvery { client.getWantedList(1) } returns response

        val result = service.getHighestRewardUrl(1)

        assertNull(result)
    }

    @Test
    fun `getHighestRewardAcrossAllPages returns highest across pages`() = runTest {

        val page1 = WantedResponse(
                total = 4,
                page = 1,
                items = listOf(
                        WantedItem(rewardText = "Up to $5,000", url = "low1"),
                        WantedItem(rewardText = "Up to $25,000", url = "mid1")
                )
        )

        val page2 = WantedResponse(
                total = 4,
                page = 2,
                items = listOf(
                        WantedItem(rewardText = "Up to $500,000", url = "highest"),
                        WantedItem(rewardText = "Up to $10,000", url = "low2")
                )
        )

        coEvery { client.getWantedList(1) } returns page1
        coEvery { client.getWantedList(2) } returns page2

        val result = service.getHighestRewardAcrossAllPages()

        assertEquals("highest", result)
    }
}
