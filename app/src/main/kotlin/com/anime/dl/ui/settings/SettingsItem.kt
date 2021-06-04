package com.anime.dl.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.R
import com.anime.dl.databinding.SettingsItemBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class SettingsItem(
    val icon: Int,
    val name: String
) : AbstractBindingItem<SettingsItemBinding>() {

    override val type: Int = R.id.fastadapter_settings_item_id
    lateinit var binding: SettingsItemBinding

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SettingsItemBinding {
        binding = SettingsItemBinding.inflate(inflater, parent, false)
        return binding
    }

    override fun bindView(binding: SettingsItemBinding, payloads: List<Any>) {
        binding.settingsItemName.text = name
        binding.settingsItemIcon.setImageResource(icon)
    }
}
