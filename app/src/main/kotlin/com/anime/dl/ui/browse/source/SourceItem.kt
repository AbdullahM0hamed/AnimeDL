package com.anime.dl.ui.browse.source

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.SourceCompactGridItemBinding
import com.anime.dl.sources.models.AnimeInfo
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class SourceItem(val anime: AnimeInfo) :
    AbstractBindingItem<SourceCompactGridItemBinding>() {

    override val type: Int = R.id.fastadapter_source_grid_item_id

    override fun bindView(binding: SourceCompactGridItemBinding, payloads: List<Any>) {
        binding.title.text = anime.title
        Glide.with(App.applicationContext()).load(anime.cover).into(binding.thumbnail)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SourceCompactGridItemBinding {
        return SourceCompactGridItemBinding.inflate(inflater, parent, false)
    }
}
