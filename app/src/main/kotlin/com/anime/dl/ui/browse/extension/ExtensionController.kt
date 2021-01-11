package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.dl.App
import com.anime.dl.actions.FindExtensions
import com.anime.dl.databinding.ExtensionControllerBinding
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.extensions.models.Extension
import com.anime.dl.R
import com.anime.dl.states.ExtensionListState
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.main.mainStore
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.reduxkotlin.StoreSubscription
import reactivecircus.flowbinding.swiperefreshlayout.refreshes

class ExtensionController : 
    BaseController<ExtensionControllerBinding>(),
    ExtensionAdapter.OnButtonClickListener {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    private var adapter: FlexibleAdapter<IFlexible<*>>? = null

    private var extensions: List<ExtensionItem> = emptyList()

    private lateinit var storeSubscription: StoreSubscription

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        storeSubscription = mainStore.subscribe { newState(mainStore.state) }

        binding.swipeRefresh.isRefreshing = true
        binding.swipeRefresh.refreshes()
            .onEach { mainStore.dispatch(FindExtensions()) }
            .launchIn(scope)

        this.adapter = ExtensionAdapter(this)

        binding.recycler.layoutManager = LinearLayoutManager(view.context)
        binding.recycler.adapter = adapter
        adapter?.fastScroller = binding.fastScroller
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)

        //Unsubscribe
        storeSubscription()
    }

    override fun onButtonClick(position: Int) {}

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        super.onChangeStarted(handler, type)
        if (type.isPush) {
            mainStore.dispatch(FindExtensions())
        }
    }

    private fun newState(state: ExtensionListState) {
        val context = App.applicationContext()

        if (state.installedExtensions.isNotEmpty()) {
            val header = ExtensionGroupItem(context.getString(R.string.ext_installed))
            this.extensions += state.installedExtensions.map { extension ->
                ExtensionItem(extension, header)
            }
        }

        if (state.availableExtensions.isNotEmpty()) {
            val header = ExtensionGroupItem(context.getString(R.string.ext_available))
            this.extensions += state.availableExtensions.map { extension ->
                ExtensionItem(extension, header)
            }
        }

        drawExtensions()
    }

    private fun drawExtensions() {
        binding.swipeRefresh.isRefreshing = false
        adapter?.updateDataSet(emptyList())
        adapter?.updateDataSet(this.extensions)
    }
}
