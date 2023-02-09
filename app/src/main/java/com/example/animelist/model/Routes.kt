package com.example.animelist.model

object Routes {
    private const val BASE_URL = "https://api.jikan.moe"
    private const val VERSION = "v4"
    const val ALL_TOP_ANIME = "$BASE_URL/$VERSION/top/anime"
    const val ANIME_BY_ID = "$BASE_URL/$VERSION/anime"
}