package com.example.animelist.ui.animelistall

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.animelist.R
import com.example.animelist.databinding.FragmentAnimeDetailsBinding
import com.example.animelist.util.Resource

class AnimeDetailsFragment : Fragment(R.layout.fragment_anime_details) {
    private var _binding: FragmentAnimeDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<AnimeDetailsFragmentArgs>()
    private val viewModel by viewModels<AnimeListViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnimeDetailsBinding.bind(view)
        viewModel.setAnimeDetailsById(args.animeId)
        binding.apply {
            retryBtn.setOnClickListener {
                errorLayout.visibility = View.GONE
                viewModel.setAnimeDetailsById(args.animeId)
            }
            viewModel.animeDetails.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Error -> {
                        errorLayout.visibility = View.VISIBLE
                    }
                    is Resource.Loading -> {
                        errorLayout.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        errorLayout.visibility = View.GONE
                        animeDesc.text = resource.data?.synopsis
                        animeName.text = resource.data?.title
                        animeImage.load(resource.data?.images?.jpg?.largeImageUrl)
                        animeEps.text = "episodes ${resource.data?.episodes}"
                    }
                }

            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}