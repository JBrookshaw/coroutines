package com.coroutines.controller

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class HighestRewardResponse(
        val url: String?
)
