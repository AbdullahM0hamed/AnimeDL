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
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.google.android.material.tabs.TabLayout

class BrowseController : 
    BaseController<BrowseControllerBinding>(),
    TabbedController {

    private var adapter: BrowseAdapter? = null

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

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
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

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        adapter = BrowseAdapter()
        binding.pager.adapter = adapter
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        adapter = null
    }

   private inner class BrowseAdapter : RouterPageAdapter(this@BrowseController) {

       private val tabTitles = listOf(
           R.string.extensions,
           R.string.sources
       )
       .map { resources!!.getString(it) }

       override fun getCount(): Int {
           return tabTitles.size
       }

       override fun getPageTitle(position: Int): CharSequence {
           return tabTitles[position]
       }

       override fun configureRouter(router: Router, position: Int) {
           return
       }
   }

   companion object {
       const val SOURCES_CONTROLLER = 0
       const val EXTENSIONS_CONTROLLER = 1
   }
}
