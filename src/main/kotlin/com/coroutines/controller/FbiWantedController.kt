package com.coroutines.controller

import com.coroutines.client.WantedResponse
import com.coroutines.service.FbiWantedService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/fbi")
class FbiWantedController(
        private val service: FbiWantedService
) {

    /**
     * GET /fbi/list?page=1
     */
    @Get("/list")
    suspend fun getWantedList(
            @QueryValue(defaultValue = "1") page: Int
    ): WantedResponse {
        return service.getWantedList(page)
    }

    /**
     * GET /fbi/highest-reward
     */
    @Get("/highest-reward")
    suspend fun getHighestRewardUrl(
    ): HighestRewardResponse {
        return HighestRewardResponse(
                url = service.getHighestRewardAcrossAllPages()
        )
    }
}
