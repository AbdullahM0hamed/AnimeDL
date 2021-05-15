package com.anime.dl.ui.anime

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.actions.UpdateAnimeInfo
import com.anime.dl.databinding.AnimeControllerBinding
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.Source
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.main.MainActivity
import com.anime.dl.ui.main.mainStore
import com.anime.dl.widget.StateImageViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.reduxkotlin.StoreSubscription
import reactivecircus.flowbinding.swiperefreshlayout.refreshes

class AnimeController : BaseController<AnimeControllerBinding> {

    constructor(anime: AnimeInfo, source: Source) : this(
        Bundle().apply {
            putString(ANI_NAME, anime.title)
        }
    ) {
        this.anime = anime
        this.source = source
    }

    @Suppress("unused")
    constructor(bundle: Bundle) : super(bundle)

    override val hasBottomNav = false

    private var source: Source? = null
        private set

    private var anime: AnimeInfo? = null
        private set

    private var recycler: RecyclerView? = null
    private var adapter: GenericFastAdapter? = null
    private var marginTop: Int? = null
    private var itemAdapter: GenericItemAdapter = items()
    private lateinit var storeSubscription: StoreSubscription
    private lateinit var params: ViewGroup.MarginLayoutParams

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = AnimeControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        
        params = (activity as MainActivity).binding.controllerContainer.layoutParams as ViewGroup.MarginLayoutParams
        marginTop = params.topMargin
        params.topMargin = 0
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        storeSubscription = mainStore.subscribe { newState(mainStore.state.animeInfoState.anime) }
        binding.swipeRefresh.isRefreshing = true
        binding.swipeRefresh.refreshes().onEach { mainStore.dispatch(UpdateAnimeInfo(anime!!, source)) }.launchIn(scope)
    }

    override fun onDestroyView(view: View) {
        params.topMargin = marginTop!!
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        adapter = null
        recycler = null

        // Unsubscribe
        storeSubscription()
        super.onDestroyView(view)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            resetActionBar()
            router.popCurrentController()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun setActionBar() {
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun newState(anime: AnimeInfo?) {
        binding.swipeRefresh.isRefreshing = false
        binding.animeTitle.text = anime?.title

        val context = App.applicationContext()
        binding.animeSource.text = source?.name
        binding.card.clipToOutline = true
        setImage(context, binding.coverImage, anime?.cover true)
        setImage(context, binding.animePoster, anime?.cover, false)
    }

    fun setImage(context: Context, view: ImageView, cover: String, blur: Boolean) {
        var image = Glide.with(context) 
            .load(cover)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .centerCrop()
            .placeholder(android.R.color.transparent)

        if (blur) {
            image.transform(BlurTransformation())
        }

        image.into(StateImageViewTarget(view, binding.progress))
    }

    protected companion object {
        const val SRC_NAME = "src_name"
        const val ANI_NAME = "ani_name"
    }
}
