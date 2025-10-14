package com.coroutines.repo

import kotlinx.coroutines.flow.Flow

interface StreamRepository {
    fun greetings(): Flow<String>
}