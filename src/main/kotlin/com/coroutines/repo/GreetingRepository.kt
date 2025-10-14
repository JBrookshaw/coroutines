package com.coroutines.repo

interface GreetingRepository {
    suspend fun getGreeting(): String
}