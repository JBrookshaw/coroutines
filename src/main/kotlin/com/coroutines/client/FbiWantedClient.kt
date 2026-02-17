package com.coroutines.client

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${fbi.api.base-url}")
interface FbiWantedClient {

    @Get("/wanted/v1/list")
    suspend fun getWantedList(
            @QueryValue page: Int? = null,
            @QueryValue title: String? = null,
            @QueryValue("field_offices") fieldOffices: String? = null,
            @QueryValue("person_classification") personClassification: String? = null,
            @Header("User-Agent") userAgent: String =
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120.0.0.0 Safari/537.36",
            @Header("Accept") accept: String = "application/json",
            @Header("Referer") referer: String = "https://www.fbi.gov/"
    ): WantedResponse
}
