package com.example.animelist.model

import android.util.Log
import androidx.annotation.DrawableRes
import com.example.animelist.R
import com.example.animelist.util.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class AnimeListApiImpl(private val client: HttpClient) : AnimeListApi {
    override suspend fun getAnimeListAll(): Resource<List<Anime>> {
//        val emptyAnimeList = NoConnectionData.animeEmptyList
        return try {
            Log.d("ANIME", client.get { url(Routes.TOP_ANIME) }.headers.toString())
            Log.d("ANIME", client.get { url(Routes.TOP_ANIME) }.body())
            val rootAnimeList: RootAnimeList = client.get { url(Routes.TOP_ANIME) }.body()
            Resource.Success(rootAnimeList.data)
        } catch (e: RedirectResponseException) {
            Log.e("AnimeApi", "3XX Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message)
        } catch (e: ClientRequestException) {
            Log.e("AnimeApi", "4XX Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message)
        } catch (e: ServerResponseException) {
            Log.e("AnimeApi", "5XX Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message)
        } catch (e: Exception) {
            Log.e("AnimeApi", "Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message!!)
        }
    }

    override suspend fun getAnimeDetails(animeId: Int): Resource<Anime> {
        return try {
            val animeDetails: RootAnime =
                client.get { url("${Routes.ANIME_BY_ID}/$animeId") }.body()
            return Resource.Success(animeDetails.data)
        } catch (e: RedirectResponseException) {
            Log.e("AnimeApi", "3XX Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message)
        } catch (e: ClientRequestException) {
            Log.e("AnimeApi", "4XX Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message)
        } catch (e: ServerResponseException) {
            Log.e("AnimeApi", "5XX Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message)
        } catch (e: Exception) {
            Log.e("AnimeApi", "Error: ${e.message}")
            Resource.Error(e.localizedMessage ?: e.message!!)
        }
    }

}


