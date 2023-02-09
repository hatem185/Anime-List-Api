package com.example.animelist.ui.animelistall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.model.Anime
import com.example.animelist.model.AnimeListApi
import com.example.animelist.model.AnimeListApiImpl
import com.example.animelist.util.Provider
import com.example.animelist.util.Resource
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private var animeListApi: AnimeListApi = AnimeListApiImpl(Provider.client)
    private val _animelist: MutableLiveData<Resource<List<Anime>>> =
        MutableLiveData(Resource.Loading())
    private val _animeDetails: MutableLiveData<Resource<Anime>> =
        MutableLiveData(Resource.Loading())
    val animelist: LiveData<Resource<List<Anime>>> get() = _animelist
    val animeDetails: LiveData<Resource<Anime>> get() = _animeDetails

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            _animelist.value = animeListApi.getAnimeListAll()
        }
    }

    fun setAnimeDetailsById(animeId: Int) {
        viewModelScope.launch {
            _animeDetails.value = animeListApi.getAnimeDetails(animeId)
        }
    }

}