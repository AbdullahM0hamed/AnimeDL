package com.anime.dl.ui.browse.source

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.EpisodeRowBinding
import com.anime.dl.databinding.EpisodeRowWithThumbnailBinding
import com.anime.dl.sources.models.EpisodeInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bluelinelabs.conductor.RouterTransaction
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class EpisodeItem(
    val episode: EpisodeInfo
) : AbstractBindingItem<ViewBinding>() {

    override val type: Int = R.id.fastadapter_episode_item_id
    private var binding: EpisodeRowBinding? = null
    private var thumbnailBinding: EpisodeRowWithThumbnailBinding? = null

    override fun bindView(binding: ViewBinding, payloads: List<Any>) {
        if (!episode.thumbnail.isNullOrEmpty()) {
            this.thumbnailBinding = binding as EpisodeRowWithThumbnailBinding?
            thumbnailBinding?.episodeTitle?.text = episode.title
            setImage(episode)
        } else {
            this.binding = binding as EpisodeRowBinding?
            binding?.episodeTitle?.text = episode.title
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ViewBinding {
        return if (episode.thumbnail.isNullOrEmpty()) {
            EpisodeRowBinding.inflate(inflater, parent, false)
        } else {
            EpisodeRowWithThumbnailBinding.inflate(inflater, parent, false)
        }
    }

    fun setImage(episode: EpisodeInfo) {
        val context = App.applicationContext()

        Glide.with(context).clear(thumbnailBinding?.episodeThumbnail)
        Glide.with(context)
            .load(episode.thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .centerCrop()
            .placeholder(android.R.color.transparent)
            .into(thumbnailBinding?.episodeThumbnail)
    }
}
