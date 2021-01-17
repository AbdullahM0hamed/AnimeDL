package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.databinding.ExtensionCardItemBinding
import com.anime.dl.extensions.models.Extension
import com.anime.dl.R
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class ExtensionItem(val extension: Extension) : 
    AbstractBindingItem<ExtensionCardItemBinding>() {

    override val type: Int = R.id.fastadapter_extension_item_id

    override fun bindView(
        binding: ExtensionCardItemBinding, 
        payloads: List<Any>
    ) {
        binding.extTitle.text = extension.name
        binding.version.text = "${extension.versionName}.${extension.versionCode}"

        if (extension.isTutorial) {
            binding.image.setImageResource(R.drawable.ic_tutorial)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ExtensionCardItemBinding {
        return ExtensionCardItemBinding.inflate(inflater, parent, false)
    }
}
