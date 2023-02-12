package com.example.animelist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val episodes: Int,
    val images: Images,
    @SerialName("mal_id")
    val malId: Int,
    val synopsis: String,
    val title: String
)