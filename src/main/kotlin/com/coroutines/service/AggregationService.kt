package com.coroutines.service

import com.coroutines.client.ServiceAClient
import com.coroutines.client.ServiceBClient
import jakarta.inject.Singleton
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

@Singleton
class AggregationService(
        private val serviceA: ServiceAClient,
        private val serviceB: ServiceBClient
) {
    suspend fun getCombinedData(): String = coroutineScope {
        // launch both calls concurrently
        val deferredA = async { serviceA.getData() }
        val deferredB = async { serviceB.getInfo() }

        // await both results
        val resultA = deferredA.await()
        val resultB = deferredB.await()

        "Combined: $resultA + $resultB"
    }
}