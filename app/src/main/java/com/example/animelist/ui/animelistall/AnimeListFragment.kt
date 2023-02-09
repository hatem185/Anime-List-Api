package com.example.animelist.ui.animelistall

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.animelist.R
import com.example.animelist.databinding.FragmentAnimeListBinding


class AnimeListFragment : Fragment(R.layout.fragment_anime_list) {
    private var _binding: FragmentAnimeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AnimeListViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnimeListBinding.bind(view)
        val adapter = AdapterAnimeList {animeId->
            val action =
                AnimeListFragmentDirections.actionAnimeListFragmentToAnimeDetailsFragment(animeId)
            findNavController().navigate(action)
        }
        binding.animeListView.adapter = adapter
        viewModel.animelist.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}