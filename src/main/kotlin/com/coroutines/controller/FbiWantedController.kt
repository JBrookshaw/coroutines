package com.coroutines.controller

import com.coroutines.client.WantedResponse
import com.coroutines.service.FbiWantedService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/fbi")
class FbiWantedController(private val service: FbiWantedService) {

    @Get("/list")
    suspend fun getWantedList(@QueryValue(defaultValue = "1") page: Int): WantedResponse =
        service.getWantedList(page)

    @Get("/highest-reward")
    suspend fun getHighestRewardUrl(): HighestRewardResponse =
        HighestRewardResponse(url = service.getHighestRewardAcrossAllPages())
}
