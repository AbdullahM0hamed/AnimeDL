package com.anime.dl.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anime.dl.R
import com.anime.dl.databinding.BrowseControllerBinding
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.ui.base.controller.TabbedController
import com.anime.dl.ui.main.MainActivity
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.google.android.material.tabs.TabLayout

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
        return resources!!.getString(R.string.browse)
    }

    override onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        super.onChangeStarted(handler, type)
        if (type.isEnter) {
            (activity as? MainActivity)?.binding?.tabs?.apply {
                setupWithViewPager(binding.pager)
            }
        }
    }

    override fun configureTabs(tabs: TabLayout) {
        with(tabs) {
            tabGravity = TabLayout.GRAVITY_FILL
            tabMode = TabLayout.MODE_FIXED
        }
    }
}
