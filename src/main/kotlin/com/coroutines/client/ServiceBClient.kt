package com.coroutines.client

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://service-b.example.com")
interface ServiceBClient {
    @Get("/info")
    suspend fun getInfo(): String
}