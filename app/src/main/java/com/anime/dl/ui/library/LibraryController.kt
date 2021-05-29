package com.anime.dl.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.anime.dl.R
import com.anime.dl.databinding.LibraryControllerBinding
import com.anime.dl.ui.base.controller.BaseController

class LibraryController : BaseController<LibraryControllerBinding>() {
    
    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = LibraryControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        binding.emptyView.isVisible = true
    }

    override fun getTitle(): String? = resources!!.getString(R.string.home)
}
