package com.example.perqaragames.model

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("background_image")
    val backgroundImage: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("released")
    val released: String? = null,

    @SerializedName("rating")
    val rating: Double? = null,

    @SerializedName("publishers")
    val publishers: List<PublisherData>? = null,

    @SerializedName("playtime")
    val playtime: Int? = null,

    @SerializedName("description")
    val description: String? = null,
)

data class PublisherData(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
)
