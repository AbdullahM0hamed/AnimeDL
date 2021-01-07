package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.dl.App
import com.anime.dl.actions.findAvailableExtensions
import com.anime.dl.databinding.ExtensionControllerBinding
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
import org.rekotlin.StoreSubscriber
import reactivecircus.flowbinding.swiperefreshlayout.refreshes

class ExtensionController : 
    BaseController<ExtensionControllerBinding>(),
    StoreSubscriber<ExtensionListState>,
    ExtensionAdapter.OnButtonClickListener {

    init {
        mainStore.subscribe(this)
    }

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    private var adapter: FlexibleAdapter<IFlexible<*>>? = null

    private var extensions: List<ExtensionItem> = emptyList()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        binding.swipeRefresh.isRefreshing = true
        binding.swipeRefresh.refreshes()
            .onEach { mainStore.dispatch(findAvailableExtensions()) }
            .launchIn(scope)

        this.adapter = ExtensionAdapter(this)

        binding.recycler.layoutManager = LinearLayoutManager(view.context)
        binding.recycler.adapter = adapter
        adapter?.fastScroller = binding.fastScroller
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        adapter = null
    }

    override fun onButtonClick(position: Int) {}

    override fun newState(state: ExtensionListState) {
        val installedExtensions = state?.installedExtensions
        val availableExtensions = state?.availableExtensions
        val context = App.applicationContext()

        extensions = mutableListOf<ExtensionItem>()
        
        if (installedExtensions!!.isNotEmpty()) {
            val header = ExtensionGroupItem(context.getString(R.string.ext_installed))
            this.extensions += installedExtensions.map { extension ->
                ExtensionItem(extension, header)
            }
        }

        if (availableExtensions!!.isNotEmpty()) {
            val header = ExtensionGroupItem(context.getString(R.string.ext_available))
            this.extensions += availableExtensions.map { extension ->
                ExtensionItem(extension, header)
            }
        }
        drawExtensions()
    }

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        super.onChangeStarted(handler, type)
        if (!type.isPush) {
            mainStore.dispatch(findAvailableExtensions())
        }
    }

    private fun drawExtensions() {
        Toast.makeText(
            App.applicationContext(),
            binding.adapter.toString(),
            5
        ).show()
        adapter?.updateDataSet(this.extensions)
    }
}
