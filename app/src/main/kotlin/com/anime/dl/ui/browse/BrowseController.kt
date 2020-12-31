package com.anime.dl.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anime.dl.databinding.BrowseControllerBinding
import com.anime.dl.ui.base.controller.BaseController

class BrowseController : BaseController() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        val binding = BrowseControllerBinding.inflate(inflater)
        return binding.root
    }
}
