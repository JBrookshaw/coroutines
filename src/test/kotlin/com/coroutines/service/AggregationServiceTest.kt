package com.coroutines.service

import com.coroutines.client.ServiceAClient
import com.coroutines.client.ServiceBClient
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.mockk.coEvery
import io.mockk.mockk
import jakarta.inject.Inject
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class AggregationServiceTest {

    @Inject lateinit var service: AggregationService
    @Inject lateinit var serviceA: ServiceAClient
    @Inject lateinit var serviceB: ServiceBClient

    @MockBean(ServiceAClient::class)
    fun mockA() = mockk<ServiceAClient>()

    @MockBean(ServiceBClient::class)
    fun mockB() = mockk<ServiceBClient>()

    @Test
    fun `should combine async results`() = runTest {
        coEvery { serviceA.getData() } returns "Data"
        coEvery { serviceB.getInfo() } returns "Info"

        val result = service.getCombinedData()

        assertEquals("Combined: Data + Info", result)
    }
}