package com.coroutines.service

import com.coroutines.repo.GreetingRepository
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jakarta.inject.Inject
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


@MicronautTest
class BasicTests (
    @Inject
    private  var service: BasicService,
    @Inject
    private var repo: GreetingRepository  // the mock from @MockBean
){

    @MockBean(GreetingRepository::class)
    fun mockRepo(): GreetingRepository = mockk(relaxed = true)

    @Test
    fun `greet uses suspend repo`() = runTest {
        coEvery { repo.getGreeting() } returns "Hi"

        val result = service.greet("Jeff")

        assertEquals("Hi, Jeff", result)
        coVerify(exactly = 1) { repo.getGreeting() }
    }
}