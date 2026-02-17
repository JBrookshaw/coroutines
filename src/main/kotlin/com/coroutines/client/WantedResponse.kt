package com.coroutines.client

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonProperty

@Serdeable
data class WantedResponse(
        @JsonProperty("total")
        val total: Int,

        @JsonProperty("items")
        val items: List<WantedItem>,

        @JsonProperty("page")
        val page: Int
)

@Serdeable
data class WantedItem(

        @JsonProperty("possible_states")
        val possibleStates: List<String>?,

        @JsonProperty("warning_message")
        val warningMessage: String?,

        @JsonProperty("field_offices")
        val fieldOffices: List<String>?,

        @JsonProperty("details")
        val details: String?,

        @JsonProperty("locations")
        val locations: List<String>?,

        @JsonProperty("age_range")
        val ageRange: String?,

        @JsonProperty("path")
        val path: String?,

        @JsonProperty("occupations")
        val occupations: List<String>?,

        @JsonProperty("eyes_raw")
        val eyesRaw: String?,

        @JsonProperty("scars_and_marks")
        val scarsAndMarks: String?,

        @JsonProperty("weight")
        val weight: String?,

        @JsonProperty("poster_classification")
        val posterClassification: String?,

        @JsonProperty("possible_countries")
        val possibleCountries: List<String>?,

        @JsonProperty("eyes")
        val eyes: String?,

        @JsonProperty("files")
        val files: List<FileItem>?,

        @JsonProperty("modified")
        val modified: String?,

        @JsonProperty("age_min")
        val ageMin: Int?,

        @JsonProperty("caution")
        val caution: String?,

        @JsonProperty("description")
        val description: String?,

        @JsonProperty("person_classification")
        val personClassification: String?,

        @JsonProperty("hair")
        val hair: String?,

        @JsonProperty("reward_max")
        val rewardMax: Int?,

        @JsonProperty("title")
        val title: String?,

        @JsonProperty("coordinates")
        val coordinates: List<Any>?,

        @JsonProperty("place_of_birth")
        val placeOfBirth: String?,

        @JsonProperty("languages")
        val languages: List<String>?,

        @JsonProperty("race_raw")
        val raceRaw: String?,

        @JsonProperty("reward_min")
        val rewardMin: Int?,

        @JsonProperty("complexion")
        val complexion: String?,

        @JsonProperty("aliases")
        val aliases: List<String>?,

        @JsonProperty("url")
        val url: String?,

        @JsonProperty("ncic")
        val ncic: String?,

        @JsonProperty("height_min")
        val heightMin: Int?,

        @JsonProperty("race")
        val race: String?,

        @JsonProperty("publication")
        val publication: String?,

        @JsonProperty("weight_max")
        val weightMax: Int?,

        @JsonProperty("subjects")
        val subjects: List<String>?,

        @JsonProperty("images")
        val images: List<ImageItem>?,

        @JsonProperty("suspects")
        val suspects: List<Any>?,

        @JsonProperty("remarks")
        val remarks: String?,

        @JsonProperty("reward_text")
        val rewardText: String?,

        @JsonProperty("nationality")
        val nationality: String?,

        @JsonProperty("legat_names")
        val legatNames: List<String>?,

        @JsonProperty("dates_of_birth_used")
        val datesOfBirthUsed: List<String>?,

        @JsonProperty("status")
        val status: String?,

        @JsonProperty("build")
        val build: String?,

        @JsonProperty("weight_min")
        val weightMin: Int?,

        @JsonProperty("hair_raw")
        val hairRaw: String?,

        @JsonProperty("uid")
        val uid: String?,

        @JsonProperty("sex")
        val sex: String?,

        @JsonProperty("height_max")
        val heightMax: Int?,

        @JsonProperty("additional_information")
        val additionalInformation: String?,

        @JsonProperty("age_max")
        val ageMax: Int?,

        @JsonProperty("pathId")
        val pathId: String?
)

@Serdeable
data class FileItem(

        @JsonProperty("url")
        val url: String?,

        @JsonProperty("name")
        val name: String?
)

@Serdeable
data class ImageItem(

        @JsonProperty("large")
        val large: String?,

        @JsonProperty("caption")
        val caption: String?,

        @JsonProperty("original")
        val original: String?,

        @JsonProperty("thumb")
        val thumb: String?
)
