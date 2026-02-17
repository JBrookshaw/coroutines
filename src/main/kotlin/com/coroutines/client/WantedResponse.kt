package com.coroutines.client

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonProperty

@Serdeable
data class WantedResponse(
        @JsonProperty("total")
        val total: Int = 0,

        @JsonProperty("items")
        val items: List<WantedItem> = emptyList(),

        @JsonProperty("page")
        val page: Int = 0
)

@Serdeable
data class WantedItem(

        @JsonProperty("url")
        val url: String? = null,

        @JsonProperty("reward_text")
        val rewardText: String? = null,

        @JsonProperty("details")
        val details: String? = null,

        @JsonProperty("locations")
        val locations: List<String> = emptyList(),

        @JsonProperty("eyes_raw")
        val eyesRaw: String? = null,

        @JsonProperty("scars_and_marks")
        val scarsAndMarks: String? = null,

        @JsonProperty("caution")
        val caution: String? = null,

        @JsonProperty("description")
        val description: String? = null,

        @JsonProperty("hair")
        val hair: String? = null,

        @JsonProperty("reward_max")
        val rewardMax: Int? = null,

        @JsonProperty("reward_min")
        val rewardMin: Int? = null,

        @JsonProperty("aliases")
        val aliases: List<String> = emptyList(),

        @JsonProperty("subjects")
        val subjects: List<String> = emptyList(),

        @JsonProperty("uid")
        val uid: String? = null,

        @JsonProperty("additional_information")
        val additionalInformation: String? = null,

        @JsonProperty("pathId")
        val pathId: String? = null
)

@Serdeable
data class FileItem(
        @JsonProperty("url")
        val url: String? = null,

        @JsonProperty("name")
        val name: String? = null
)

@Serdeable
data class ImageItem(
        @JsonProperty("large")
        val large: String? = null,

        @JsonProperty("caption")
        val caption: String? = null,

        @JsonProperty("original")
        val original: String? = null,

        @JsonProperty("thumb")
        val thumb: String? = null
)


