package com.anime.dl.ui.base.controller

import android.view.View
import com.anime.dl.databinding.PlaceholderBinding
import com.anime.dl.ui.main.MainActivity

class PlaceholderController : BaseController<PlaceholderBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = BrowseControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onChangeStarted(handler: ControllerChangeHandler, type: ControllerChangeType) {
        super.onChangeStarted(handler, type)
        if (type.isEnter) {
            (activity as? MainActivity)?.binding?.tabs?.setVisibility(View.GONE)
        }
    }
}
