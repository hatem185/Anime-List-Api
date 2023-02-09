package com.example.animelist.model

interface AnimeListApi {
    suspend fun getAnimeListAll(): List<Anime>
    suspend fun getAnimeListOne(animeId:Int): Anime


}