package com.anime.dl.ui.browse.extension

import android.view.View
import com.anime.dl.databinding.ExtensionCardItemBinding
import eu.davidea.viewholders.FlexibleViewHolder

class ExtensionHolder(view: View, val adapter: ExtensionAdapter) : 
    FlexibleViewHolder(view, adapter) {
        
        private val binding = ExtensionCardItemBinding.bind(view)

        init {
            binding.extButton.setOnClickListener {
                adapter.buttonClickListener.onButtonClick(bindingAdapterPosition)
            }
        }

        fun bind(item: ExtensionItem) {
            val extension = item.extension

            binding.extTitle.text = extension.name
            binding.version.text = extension.versionName
        }
}
