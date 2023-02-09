package com.example.animelist.model

import com.example.animelist.util.Resource

interface AnimeListApi {
    suspend fun getAnimeListAll():Resource<List<Anime>>
    suspend fun getAnimeListOne(animeId:Int): Anime


}