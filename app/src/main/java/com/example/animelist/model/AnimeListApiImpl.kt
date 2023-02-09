package com.example.animelist.model

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class AnimeListApiImpl(private val client: HttpClient) : AnimeListApi {
    override suspend fun getAnimeListAll(): List<Anime> {
        return try {
            val rootanime: RootAnimeList = client.get { url(Routes.ALL_TOP_ANIME) }.body()
            Log.d("TAG", "log log ${rootanime.data}")
            return rootanime.data
        } catch (e: RedirectResponseException) {
            Log.e("AnimeApi", "3XX Error: ${e.message}")
            emptyList()
        } catch (e: ClientRequestException) {
            Log.e("AnimeApi", "4XX Error: ${e.message}")
            emptyList()
        } catch (e: ServerResponseException) {
            Log.e("AnimeApi", "5XX Error: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            Log.e("AnimeApi", "Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getAnimeListOne(animeId: Int): Anime {
        return try {
            val rootanime: RootAnime =
                client.get { url("${Routes.ANIME_BY_ID}/$animeId") }.body()
            return rootanime.data
        } catch (e: RedirectResponseException) {
            Log.e("AnimeApi", "3XX Error: ${e.message}")
            Anime(0, Images(Jpg("")), 0, "", "")
        } catch (e: ClientRequestException) {
            Log.e("AnimeApi", "4XX Error: ${e.message}")
            Anime(0, Images(Jpg("")), 0, "", "")

        } catch (e: ServerResponseException) {
            Log.e("AnimeApi", "5XX Error: ${e.message}")
            Anime(0, Images(Jpg("")), 0, "", "")

        } catch (e: Exception) {
            Log.e("AnimeApi", "Error: ${e.message}")
            Anime(0, Images(Jpg("")), 0, "", "")

        }
    }
}


