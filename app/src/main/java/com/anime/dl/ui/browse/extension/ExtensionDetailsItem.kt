package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.databinding.ExtensionDetailHeaderBinding
import com.anime.dl.extensions.models.Extension
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class ExtensionDetailsItem(val extension: Extension?) :
    AbstractBindingItem<ExtensionDetailHeaderBinding>() {

    override val type: Int = R.id.fastadapter_extension_details_id

    override fun bindView(binding: ExtensionDetailHeaderBinding, payloads: List<Any>) {
        val context = App.applicationContext()
        binding.extensionTitle.text = extension?.name
        binding.extensionVersion.text = context.getString(R.string.ext_version, "${extension?.versionName}.${extension?.versionCode}")
        binding.extensionLang.text = context.getString(R.string.ext_lang, extension?.lang)
        binding.extensionPkg.text = extension?.pkgName
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ExtensionDetailHeaderBinding {
        return ExtensionDetailHeaderBinding.inflate(inflater, parent, false)
    }
}
