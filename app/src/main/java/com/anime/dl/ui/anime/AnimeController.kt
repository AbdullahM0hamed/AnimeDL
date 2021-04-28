package com.anime.dl.ui.anime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.AnimeControllerBinding
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.Source
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.main.mainStore
import com.anime.dl.widget.StateImageViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import org.reduxkotlin.StoreSubscription

class AnimeController : BaseController<AnimeControllerBinding> {

    constructor(anime: AnimeInfo) : this(
        Bundle().apply {
            putString(ANI_NAME, anime.title)
        }
    ) {
        this.anime = anime
    }

    @Suppress("unused")
    constructor(bundle: Bundle) : super(bundle)

    override val hasBottomNav = false

    /*private var source: Source? = null
        private set*/

    private var anime: AnimeInfo? = null
        private set

    private var recycler: RecyclerView? = null
    private var adapter: GenericFastAdapter? = null
    private var itemAdapter: GenericItemAdapter = items()
    private lateinit var storeSubscription: StoreSubscription

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = AnimeControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        val context = App.applicationContext()
        android.widget.Toast.makeText(context, anime?.cover?.isNotNullOrEmpty()?.toString(), 5).show()
        if (anime?.cover?.isNotNullOrEmpty()) {
            Glide.with(context)
                .load(anime?.cover)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .placeholder(android.R.color.transparent)
                .into(StateImageViewTarget(binding.coverImage, binding.progress))
        }
    }

    override fun onDestroyView(view: View) {
        adapter = null
        recycler = null

        // Unsubscribe
        //storeSubscription()

        super.onDestroyView(view)
    }

    protected companion object {
        const val SRC_NAME = "src_name"
        const val ANI_NAME = "ani_name"
    }
}
