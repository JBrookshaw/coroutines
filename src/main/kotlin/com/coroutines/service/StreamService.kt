package com.coroutines.service

import com.coroutines.repo.StreamRepository
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class StreamService(private val repo: StreamRepository) {
    fun greetingsFor(name: String): Flow<String> =
        repo.greetings().map { "$it, $name" }
}
