package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anime.dl.base.controller.BaseController
import com.anime.dl.databing.ExtensionControllerBinding

class ExtensionController : BaseController<ExtensionControllerBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }
}
