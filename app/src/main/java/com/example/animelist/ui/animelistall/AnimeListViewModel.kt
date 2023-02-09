package com.example.animelist.ui.animelistall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.model.Anime
import com.example.animelist.model.AnimeListApi
import com.example.animelist.model.AnimeListApiImpl
import com.example.animelist.util.Provider
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private var animeListApi: AnimeListApi = AnimeListApiImpl(Provider.client)
    private val _animelist: MutableLiveData<List<Anime>> = MutableLiveData()
    val animelist: LiveData<List<Anime>> get() = _animelist

    init {
        loadAnimeListFromApi()
    }

    private fun loadAnimeListFromApi() {
        viewModelScope.launch {
            _animelist.value = animeListApi.getAnimeListAll()
        }
    }

    fun setViewWithAnimeDataByIdFromApi(
        animeId: Int,
        setAnimeDetails: (anime: Anime) -> Unit
    ) {
        viewModelScope.launch {
            setAnimeDetails(animeListApi.getAnimeListOne(animeId))
        }
    }

}