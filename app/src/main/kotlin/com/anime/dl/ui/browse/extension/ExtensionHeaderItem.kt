package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anime.dl.R
import com.anime.dl.databinding.SourceMainControllerCardHeaderBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ExtensionHeaderItem(val name: String) :
    AbstractBindingItem<SourceMainControllerCardHeaderBinding>() {
  override val type: Int = R.id.fastadapter_extension_header_id

  override fun bindView(binding: SourceMainControllerCardHeaderBinding, payloads: List<Any>) {
    binding.title.text = name
  }

  override fun createBinding(
      inflater: LayoutInflater, parent: ViewGroup?
  ): SourceMainControllerCardHeaderBinding {
    return SourceMainControllerCardHeaderBinding.inflate(inflater, parent, false)
  }
}
