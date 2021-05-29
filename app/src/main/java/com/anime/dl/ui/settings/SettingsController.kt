package com.anime.dl.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.dl.R
import com.anime.dl.databinding.ExtensionControllerBinding
import com.anime.dl.ui.base.controller.BaseController
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion

class SettingsController : BaseController<ExtensionControllerBinding>() {
    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        val itemAdapter: GenericItemAdapter = Companion.items()
        val adapter: GenericFastAdapter = FastAdapter.with(listOf(itemAdapter))

        binding.recycler.layoutManager = LinearLayoutManager(view.context)
        binding.recycler.adapter = adapter

        val settings = mutableListOf<GenericItem>()

        settings.add(SettingsItem(R.drawable.ic_general_24dp, resources!!.getString(R.string.settings_general)))
        settings.add(SettingsItem(R.drawable.ic_palette_24dp, resources!!.getString(R.string.settings_appearance)))
        settings.add(SettingsItem(R.drawable.ic_player_24dp, resources!!.getString(R.string.settings_player)))
        settings.add(SettingsItem(R.drawable.ic_download_fill_24dp, resources!!.getString(R.string.settings_downloads)))
        settings.add(SettingsItem(R.drawable.ic_browse_fill_24dp, resources!!.getString(R.string.settings_extensions)))
        settings.add(SettingsItem(R.drawable.ic_backup_24dp, resources!!.getString(R.string.settings_backup)))
        settings.add(SettingsItem(R.drawable.ic_advanced_24dp, resources!!.getString(R.string.settings_advanced)))

        itemAdapter.set(settings)
    }

    override fun getTitle(): String? = resources!!.getString(R.string.settings)
}
