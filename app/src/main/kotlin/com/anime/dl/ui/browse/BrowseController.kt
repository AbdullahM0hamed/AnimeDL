package com.anime.dl.ui.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.actions.FindExtensions
import com.anime.dl.databinding.ExtensionControllerBinding
import com.anime.dl.extensions.models.Extension
import com.anime.dl.states.ExtensionListState
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.browse.extension.ExtensionHeaderItem
import com.anime.dl.ui.browse.extension.ExtensionItem
import com.anime.dl.ui.main.mainStore
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.reduxkotlin.StoreSubscription
import reactivecircus.flowbinding.swiperefreshlayout.refreshes

class BrowseController : BaseController<ExtensionControllerBinding>() {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    public var itemAdapter: GenericItemAdapter = items()
    private lateinit var adapter: GenericFastAdapter

    private var extensions = mutableListOf<GenericItem>()

    private lateinit var storeSubscription: StoreSubscription

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        storeSubscription = mainStore.subscribe { newState(mainStore.state) }

        binding.swipeRefresh.isRefreshing = true
        binding.swipeRefresh.refreshes().onEach { mainStore.dispatch(FindExtensions()) }.launchIn(scope)

        adapter = FastAdapter.with(listOf(itemAdapter))
        adapter?.setHasStableIds(true)

        binding.recycler.layoutManager = LinearLayoutManager(view.context)
        binding.recycler.adapter = adapter
        // adapter?.fastScroller = binding.fastScroller
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)

        // Unsubscribe
        storeSubscription()
    }

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        super.onChangeStarted(handler, type)
        if (type.isPush) {
            mainStore.dispatch(FindExtensions())
        }
    }

    override fun getTitle(): String? {
        return resources!!.getString(R.string.browse)
    }

    private fun newState(state: ExtensionListState) {
        val context = App.applicationContext()
        extensions = mutableListOf<GenericItem>()

        if (state.installedExtensions.isNotEmpty()) {
            val header = ExtensionHeaderItem(context.getString(R.string.ext_installed))
            header.identifier = 0

            if (header !in extensions) extensions.add(header)
            state.installedExtensions.mapIndexed { index, extension ->
                val item = createItem(extension, (index + 1).toLong())
                if (item !in extensions) extensions.add(item)
            }
        }

        if (state.availableExtensions.isNotEmpty()) {
            val header = ExtensionHeaderItem(context.getString(R.string.ext_available))
            header.identifier = extensions.size.toLong()

            if (header !in extensions) extensions.add(header)
            state.availableExtensions.mapIndexed { index, extension ->
                val item = createItem(extension, header.identifier + (index + 1).toLong())
                if (item !in extensions) extensions.add(item)
            }
        }

        drawExtensions()
    }

    private fun createItem(extension: Extension, id: Long): ExtensionItem {
        val item = ExtensionItem(extension)
        item.identifier = id

        return item
    }

    private fun drawExtensions() {
        binding.swipeRefresh.isRefreshing = false

        itemAdapter.set(extensions)
    }
}
