package com.example.animelist.model

import kotlinx.serialization.Serializable

@Serializable
data class RootAnimeList(
    val data: List<Anime>
)

@Serializable
data class RootAnime(
    val data: Anime
)

