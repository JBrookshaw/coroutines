package com.coroutines.client

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://service-a.example.com")
interface ServiceAClient {
    @Get("/data")
    suspend fun getData(): String
}