package com.coroutines.service

import com.coroutines.repo.GreetingRepository
import jakarta.inject.Singleton

@Singleton
class BasicService(private val repo: GreetingRepository) {
    suspend fun greet(name: String): String {
        val g = repo.getGreeting()
        return "$g, $name"
    }
}