package com.example.animelist.ui.animelistall

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.animelist.R
import com.example.animelist.databinding.FragmentAnimeDetailsBinding

class AnimeDetailsFragment : Fragment(R.layout.fragment_anime_details) {
    private var _binding: FragmentAnimeDetailsBinding? = null
    private val binding get() = _binding!!
    private val arges by navArgs<AnimeDetailsFragmentArgs>()
    private val viewModel by viewModels<AnimeListViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnimeDetailsBinding.bind(view)

        binding.apply {
            viewModel.setViewWithAnimeDataByIdFromApi(arges.animeId) { myAnime ->
                animeDesc.text = myAnime.synopsis
                animeName.text = myAnime.title
                animeImage.load(myAnime.images.jpg.large_image_url)
                animeEps.text = "episodes ${myAnime.episodes}"
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}