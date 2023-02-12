package com.example.animelist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jpg(
    @SerialName("large_image_url")
    val largeImageUrl: String,
    val localImgId: Int = 0
)