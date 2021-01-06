package com.anime.dl.ui.browse.extension

import android.view.View
import com.anime.dl.databinding.SourceMainControllerCardHeaderBinding
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.viewholders.FlexibleViewHolder

class ExtensionGroupHolder(
    view: View,
    adapter: FlexibleAdapter<*>
) : FlexibleViewHolder(view, adapter) {

    private val binding = SourceMainControllerCardHeaderBinding.bind(view)

    fun bind(item: ExtensionGroupItem) {
        var text = item.name
        binding.title.text = text
    }
}
