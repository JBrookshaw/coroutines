package com.coroutines.client

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class FbiWantedClientTest {

    @Inject
    lateinit var client: FbiWantedClient

    @Test
    fun `should fetch wanted list`() = kotlinx.coroutines.runBlocking {

        val response = client.getWantedList(page = 1)

        assertNotNull(response)
        assertTrue(response.total > 0)
        assertFalse(response.items.isEmpty())

        val firstItem = response.items.first()
        assertNotNull(firstItem.uid)
    }
}
