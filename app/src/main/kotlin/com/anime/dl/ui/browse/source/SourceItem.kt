package com.anime.dl.ui.browse.source

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.SourceCompactGridItemBinding
import com.anime.dl.sources.models.AnimeInfo
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class SourceItem(val anime: AnimeInfo) :
    AbstractBindingItem<SourceCompactGridItemBinding>() {

    override val type: Int = R.id.fastadapter_source_grid_item_id

    override fun bindView(binding: SourceCompactGridItemBinding, payloads: List<Any>) {
        binding.title.text = anime.title
        setImage(anime)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SourceCompactGridItemBinding {
        return SourceCompactGridItemBinding.inflate(inflater, parent, false)
    }

    fun setImage(anime: AnimeInfo) {
        val context = App.applicationContext()
        binding.card.clipToOutline = true

        GlideApp.with(context).clear(binding.thumbnail)
        if (!anime.cover.isNullOrEmpty()) {
            GlideApp.with(context)
                .load(anime.cover)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .placeholder(android.R.color.transparent)
                .into(StateImageViewTarget(binding.thumbnail, binding.progress))
        }
    }
}
