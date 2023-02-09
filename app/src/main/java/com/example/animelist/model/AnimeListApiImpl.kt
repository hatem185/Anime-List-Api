package com.example.animelist.model

import android.util.Log
import com.example.animelist.util.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class AnimeListApiImpl(private val client: HttpClient) : AnimeListApi {
    override suspend fun getAnimeListAll(): Resource<List<Anime>> {
        return try {
            val rootanime: RootAnimeList = client.get { url(Routes.ALL_TOP_ANIME) }.body()
            Resource.Success(rootanime.data)
        } catch (e: RedirectResponseException) {
            Log.e("AnimeApi", "3XX Error: ${e.message}")
            Resource.Error(e.message, emptyList())
        } catch (e: ClientRequestException) {
            Log.e("AnimeApi", "4XX Error: ${e.message}")
            Resource.Error(e.message, emptyList())
        } catch (e: ServerResponseException) {
            Log.e("AnimeApi", "5XX Error: ${e.message}")
            Resource.Error(e.message, emptyList())
        } catch (e: Exception) {
            Log.e("AnimeApi", "Error: ${e.message}")
            Resource.Error(e.message!!, emptyList())
        }
    }

    override suspend fun getAnimeDetails(animeId: Int): Resource<Anime> {
        return try {
            val animeDetails: RootAnime = client.get { url("${Routes.ANIME_BY_ID}/$animeId") }.body()
            return Resource.Success(animeDetails.data)
        } catch (e: RedirectResponseException) {
            Log.e("AnimeApi", "3XX Error: ${e.message}")
            Resource.Error(e.message)
        } catch (e: ClientRequestException) {
            Log.e("AnimeApi", "4XX Error: ${e.message}")
            Resource.Error(e.message)
        } catch (e: ServerResponseException) {
            Log.e("AnimeApi", "5XX Error: ${e.message}")
            Resource.Error(e.message)
        } catch (e: Exception) {
            Log.e("AnimeApi", "Error: ${e.message}")
            Resource.Error(e.message!!)
        }
    }
}


