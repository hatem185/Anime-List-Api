package com.example.animelist.model

import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val episodes: Int,
    val images: Images,
    val mal_id: Int,
    val title: String,
    val synopsis: String
)