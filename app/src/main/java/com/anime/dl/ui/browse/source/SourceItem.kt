package com.anime.dl.ui.browse.source

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.actions.AnimeInfoResult
import com.anime.dl.databinding.SourceCompactGridItemBinding
import com.anime.dl.sources.Source
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.ui.anime.AnimeController
import com.anime.dl.ui.browse.source.SourceController
import com.anime.dl.ui.main.mainStore
import com.anime.dl.widget.StateImageViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bluelinelabs.conductor.RouterTransaction
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class SourceItem(
    val anime: AnimeInfo,
    val source: Source,
    val controller: SourceController
) : AbstractBindingItem<SourceCompactGridItemBinding>() {

    override val type: Int = R.id.fastadapter_source_grid_item_id
    private lateinit var binding: SourceCompactGridItemBinding

    override fun bindView(binding: SourceCompactGridItemBinding, payloads: List<Any>) {
        this.binding = binding
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
        binding.root.setOnClickListener {
            mainStore.dispatch(AnimeInfoResult(null, null))
            controller.router.pushController(RouterTransaction.with(AnimeController(anime, source)))
        }

        Glide.with(context).clear(binding.thumbnail)
        if (!anime.cover.isNullOrEmpty()) {
            Glide.with(context)
                .load(anime.cover)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .placeholder(android.R.color.transparent)
                .into(StateImageViewTarget(binding.thumbnail, binding.progress))
        }
    }
}
