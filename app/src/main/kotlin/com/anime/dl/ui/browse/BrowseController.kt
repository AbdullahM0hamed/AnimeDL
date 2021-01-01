package com.anime.dl.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anime.dl.databinding.BrowseControllerBinding
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.base.controller.TabbedController

class BrowseController : 
    BaseController<BrowseControllerBinding>(),
    TabbedController {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = BrowseControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun getTitle(): String? {
        return "Placeholder"
    }
}
