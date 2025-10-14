package com.coroutines.service

import com.coroutines.repo.StreamRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import io.micronaut.test.annotation.MockBean
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class StreamServiceFlowTest {

    @Inject lateinit var service: StreamService
    @Inject lateinit var repo: StreamRepository

    @MockBean(StreamRepository::class)
    fun mockStreamRepo(): StreamRepository = mockk(relaxed = true)

    @Test
    fun `maps greetings flow`() = runTest {
        every { repo.greetings() } returns flowOf("Hello", "Yo")

        val items = service.greetingsFor("Jeff").toList()

        assertEquals(listOf("Hello, Jeff", "Yo, Jeff"), items)
    }
}