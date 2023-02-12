package com.example.animelist.ui.animelistall

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animelist.databinding.AnimeItemBinding
import com.example.animelist.model.Anime
import com.example.animelist.model.Routes
import io.ktor.client.engine.*

class AdapterAnimeList(val readMoreNavigateClicker: (Int) -> Unit) :
    ListAdapter<Anime, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Anime>() {

            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.malId == newItem.malId
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
        init {
            itemBinding.readMoreText.apply {
                setOnClickListener {
                    val pos = adapterPosition
                    if (pos != RecyclerView.NO_POSITION)
                        readMoreNavigateClicker(getItem(pos).malId)
                    else
                        Toast.makeText(context, "The Anime is not found", Toast.LENGTH_SHORT).show()
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Anime) {
            itemBinding.apply {
                tvAnimeName.text = item.title
                tvEps.text ="episodes "+ if (item.episodes == -1) "incomplete"
                else item.episodes.toString()
                tvDesc.text = item.synopsis
                tvImg.load(item.images.jpg.largeImageUrl)
            }
        }

    }
}
