package com.anime.dl.ui.base.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anime.dl.R
import com.anime.dl.databinding.PlaceholderBinding
import com.anime.dl.ui.main.MainActivity
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType

class PlaceholderController : BaseController<PlaceholderBinding>() {

  override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
    binding = PlaceholderBinding.inflate(inflater)
    return binding.root
  }

  override fun getTitle(): String? {
    return resources!!.getString(R.string.app_name)
  }

  override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
    super.onChangeStarted(handler, type)
    if (type.isEnter) {
      (activity as? MainActivity)?.binding?.tabs?.setVisibility(View.GONE)
    }
  }
}
