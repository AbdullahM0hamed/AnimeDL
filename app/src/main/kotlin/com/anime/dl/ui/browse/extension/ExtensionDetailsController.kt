package com.anime.dl.ui.browse.extension

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.ExtensionDetailsControllerBinding
import com.anime.dl.extensions.ExtensionManager
import com.anime.dl.extensions.models.Extension
import com.anime.dl.ui.base.controller.BaseController
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items

class ExtensionDetailsController(val bundle: Bundle) : BaseController<ExtensionDetailsControllerBinding>() {

    constructor(extension: Extension) : this(
        Bundle().apply {
            putString(PKG_NAME, extension.pkgName)
        }
    )

    private var genericItems = mutableListOf<GenericItem>()
    public var itemAdapter: GenericItemAdapter = items()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ExtensionDetailsControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun getTitle(): String? {
        return resources!!.getString(R.string.extension_info)
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        val manager = ExtensionManager(App.applicationContext())
        val extension = manager.getExtension(bundle.getString(PKG_NAME) as String)

        val detailItem = ExtensionDetailsItem(extension)
        genericItems.add(detailItem)

        binding.extensionPrefsRecycler.layoutManager = LinearLayoutManager(view.context)
        binding.extensionPrefsRecycler.adapter = FastAdapter.with(listOf(itemAdapter))
        itemAdapter.set(genericItems)
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

    protected companion object {
        const val PKG_NAME = "pkg_name"
    }
}
