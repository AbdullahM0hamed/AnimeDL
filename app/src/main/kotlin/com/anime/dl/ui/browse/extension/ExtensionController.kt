package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anime.dl.databinding.ExtensionControllerBinding
import com.anime.dl.ui.base.controller.BaseController

class ExtensionController : BaseController<ExtensionControllerBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }
}
