package com.example.animelist.ui.animelistall

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.animelist.R
import com.example.animelist.databinding.FragmentAnimeListBinding
import com.example.animelist.util.Resource


class AnimeListFragment : Fragment(R.layout.fragment_anime_list) {
    private var _binding: FragmentAnimeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AnimeListViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnimeListBinding.bind(view)
        val adapter = AdapterAnimeList(this::readMoreNavigateClicker)
        binding.apply {
            animeListView.adapter = adapter
            retryBtn.setOnClickListener {
                errorLayout.visibility = View.GONE
                viewModel.loadAnimeList()
            }
            viewModel.animelist.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Error -> {
                        errorLayout.visibility = View.VISIBLE
                        errorMsg.text = resource.message
                    }
                    is Resource.Loading -> {
                        errorLayout.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        errorLayout.visibility = View.GONE
                        adapter.submitList(resource.data)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun readMoreNavigateClicker(animeId: Int) {
        val action =
            AnimeListFragmentDirections.actionAnimeListFragmentToAnimeDetailsFragment(animeId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}