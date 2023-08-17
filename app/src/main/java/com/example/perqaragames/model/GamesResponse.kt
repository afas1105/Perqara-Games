package com.example.perqaragames.model

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("results")
    val results: List<ResultData>? = null
)

data class ResultData(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("background_image")
    val backgroundImage: String? = null,

    @SerializedName("rating")
    val rating: Double? = null,

    @SerializedName("released")
    val released: String? = null,
)
