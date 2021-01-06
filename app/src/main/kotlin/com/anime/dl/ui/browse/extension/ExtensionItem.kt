package com.anime.dl.ui.browse.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anime.dl.extensions.models.Extension
import com.anime.dl.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractSectionableItem
import eu.davidea.flexibleadapter.items.IFlexible

data class ExtensionItem(
    val extension: Extension,
    val header: ExtensionGroupItem? = null
) :
    AbstractSectionableItem<ExtensionHolder, ExtensionGroupItem>(header) {

        override fun getLayoutRes(): Int {
            return R.layout.extension_card_item
        }

        override fun createViewHolder(
            view: View,
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
        ): ExtensionHolder {
            return ExtensionHolder(view, adapter as ExtensionAdapter)
        }

        override fun bindViewHolder(
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
            holder: ExtensionHolder,
            position: Int,
            payloads: List<Any?>?
        ) {
            holder.bind(this)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return extension.pkgName == (other as ExtensionItem).extension.pkgName
        }

        override fun hashCode(): Int {
            return extension.pkgName.hashCode()
        }
}
