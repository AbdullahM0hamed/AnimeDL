package com.anime.dl.ui.browse.source

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.EpisodeRowBinding
import com.anime.dl.sources.models.EpisodeInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bluelinelabs.conductor.RouterTransaction
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class EpisodeItem(
    val episode: EpisodeInfo
) : AbstractBindingItem<EpisodeRowBinding>() {

    override val type: Int = R.id.fastadapter_episode_item_id
    private lateinit var binding: EpisodeRowBinding

    override fun bindView(binding: EpisodeRowBinding, payloads: List<Any>) {
        this.binding = binding
        binding.episodeTitle.text = episode.title
        setImage(episode)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): EpisodeRowBinding {
        return EpisodeRowBinding.inflate(inflater, parent, false)
    }

    fun setImage(episode: EpisodeInfo) {
        val context = App.applicationContext()

        Glide.with(context).clear(binding.episodeThumbnail)
        if (!episode.thumbnail.isNullOrEmpty()) {
            Glide.with(context)
                .load(episode.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .placeholder(android.R.color.transparent)
                .into(binding.episodeThumbnail)
        } else {
            binding.episodeThumbnail.isVisible = false
        }
    }
}
