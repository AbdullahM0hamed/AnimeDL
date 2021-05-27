package com.anime.dl.ui.browse.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.actions.BrowseAnimeResult
import com.anime.dl.databinding.SourceControllerBinding
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.extensions.models.Extension
import com.anime.dl.sources.Source
import com.anime.dl.states.BrowseAnimeState
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.main.mainStore
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import kotlin.concurrent.thread
import org.reduxkotlin.StoreSubscription

class SourceController(val bundle: Bundle) : BaseController<SourceControllerBinding>() {

    constructor(extension: Extension) : this(
        Bundle().apply {
            putString(PKG_NAME, extension.pkgName)
        }
    )

    override val hasBottomNav = false

    private var source: Source? = null
    private var recycler: RecyclerView? = null
    private var adapter: GenericFastAdapter? = null
    private var itemAdapter: GenericItemAdapter = items()
    private lateinit var storeSubscription: StoreSubscription

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = SourceControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun getTitle(): String? {
        return source?.name
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        storeSubscription = mainStore.subscribe { newState(mainStore.state.browseAnimeState) }

        val context = App.applicationContext()
        val manager = ExtensionManager(context)
        source = manager.getSource(bundle.getString(PKG_NAME) as String)

        val gridLayoutManager = GridLayoutManager(context, 3)
        setupRecycler(view, gridLayoutManager)
        binding.progress.isVisible = true
    }

    override fun onDestroyView(view: View) {
        adapter = null
        recycler = null

        // Unsubscribe
        storeSubscription()

        super.onDestroyView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.browse, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.queryHint = resources!!.getString(R.string.action_search) + "..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQuerySubmit(query: String): Boolean {
                itemAdapter.clear()
                thread {
                    val browseList = source!!.getSearchList(query, 1)
                    activity!!.runOnUiThread {
                        mainStore.dispatch(BrowseAnimeResult(browseList))
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean = true
        })
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

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        hideProgressBar()
    }

    protected companion object {
        const val PKG_NAME = "pkg_name"
    }

    fun setupRecycler(view: View, gridLayoutManager: GridLayoutManager) {
        val oldRecycler = binding.catalogueView.getChildAt(1)

        if (oldRecycler is RecyclerView) {
            oldRecycler.adapter = null
            binding.catalogueView.removeView(oldRecycler)
        }

        recycler = RecyclerView(view.context).apply {
            id = R.id.recycler
            layoutManager = gridLayoutManager
            layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        adapter = FastAdapter.with(listOf(itemAdapter))
        recycler?.adapter = adapter
        binding.catalogueView.addView(recycler)

        if (mainStore.state.browseAnimeState?.browseAnime == null) {
            getBrowseAnime()
        }
    }

    fun hideProgressBar() {
        binding.progress.isVisible = false
    }

    fun getBrowseAnime() {
        binding.progress.isVisible = true
        thread {
            val browseList = source!!.getAnimeList(1)
            activity!!.runOnUiThread {
                mainStore.dispatch(BrowseAnimeResult(browseList))
            }
        }
    }

    fun newState(state: BrowseAnimeState) {
        if (state?.browseAnime != null) {
            hideProgressBar()
        }

        val items = mutableListOf<GenericItem>()

        state.browseAnime?.anime?.map {
            items.add(SourceItem(it, source as Source, this))
        }

        itemAdapter.set(items)
    }
}
