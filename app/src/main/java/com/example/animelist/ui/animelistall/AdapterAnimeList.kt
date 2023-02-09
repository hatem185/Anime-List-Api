package com.example.animelist.ui.animelistall

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animelist.databinding.AnimeItemBinding
import com.example.animelist.model.Anime

class AdapterAnimeList(val readMoreClicker: (Int) -> Unit) :
    ListAdapter<Anime, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Anime>() {

            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.mal_id == newItem.mal_id
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.episodes == newItem.episodes || oldItem.title == newItem.title ||
                        oldItem.images == newItem.images || oldItem.synopsis == newItem.synopsis


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val binding: AnimeItemBinding =
            AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = getItem(position)
            holder.bind(item)
        }
    }

    inner class ViewHolder(private val itemBinding: AnimeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Anime) {
            itemBinding.apply {
                readMoreText.setOnClickListener {
                    readMoreClicker(getItem(adapterPosition).mal_id)
                }
                tvAnimeName.text = item.title
                tvEps.text = "episodes ${item.episodes}"
                tvDesc.text = item.synopsis
                tvDesc.maxLines = 3
                tvImg.load(item.images.jpg.large_image_url)
            }
        }
    }
}