package com.anime.dl.ui.browse.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anime.dl.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractHeaderItem
import eu.davidea.flexibleadapter.items.IFlexible

data class ExtensionGroupItem(val name: String) :
    AbstractHeaderItem<ExtensionGroupHolder>() {

    override fun getLayoutRes(): Int {
        return R.layout.source_main_controller_card_header
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): ExtensionGroupHolder {
        return ExtensionGroupHolder(view, adapter)
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: ExtensionGroupHolder,
        position: Int,
        payloads: List<Any?>?
    ) {
        holder.bind(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is ExtensionGroupItem) {
            return name == other.name
        }
        return false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
