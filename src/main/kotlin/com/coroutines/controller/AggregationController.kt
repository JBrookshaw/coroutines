package com.coroutines.controller

import com.coroutines.service.AggregationService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/aggregate")
class AggregationController(private val service: AggregationService) {
    @Get("/")
    suspend fun getCombined(): String = service.getCombinedData()
}
