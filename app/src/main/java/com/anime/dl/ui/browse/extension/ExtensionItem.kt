package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.R
import com.anime.dl.actions.InstallExtension
import com.anime.dl.databinding.ExtensionCardItemBinding
import com.anime.dl.extensions.models.Extension
import com.anime.dl.ui.browse.BrowseController
import com.anime.dl.ui.browse.source.SourceController
import com.anime.dl.ui.main.mainStore
import com.bluelinelabs.conductor.RouterTransaction
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class ExtensionItem(
    val extension: Extension,
    val controller: BrowseController
) :
    AbstractBindingItem<ExtensionCardItemBinding>() {

    override val type: Int = R.id.fastadapter_extension_item_id
    public lateinit var binding: ExtensionCardItemBinding

    override fun bindView(binding: ExtensionCardItemBinding, payloads: List<Any>) {
        binding.extTitle.text = extension.name
        binding.version.text = "${extension.versionName}.${extension.versionCode}"

        if (extension is Extension.Installed) {
            binding.extButton.setImageResource(R.drawable.ic_settings_fill_24dp)
        }

        binding.extButton.setOnClickListener {
            when (extension) {
                is Extension.Available -> {
                    val installAction: InstallExtension = InstallExtension(extension)
                    mainStore.dispatch(installAction)
                }
                else -> { openDetails(extension) }
            }
        }

        if (extension.isTutorial) {
            binding.image.setImageResource(R.drawable.ic_tutorial)
        }

        binding.root.setOnClickListener {
            controller.router.pushController(RouterTransaction.with(SourceController(extension)))
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ExtensionCardItemBinding {
        binding = ExtensionCardItemBinding.inflate(inflater, parent, false)
        return binding
    }

    private fun openDetails(extension: Extension) {
        val detailController = ExtensionDetailsController(extension)
        controller!!.router.pushController(RouterTransaction.with(detailController))
    }
}
