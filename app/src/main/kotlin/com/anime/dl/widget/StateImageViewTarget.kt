package com.anime.dl.widget

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.anime.dl.R
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.transition.Transition

class StateImageViewTarget(
    view: ImageView,
    val progress: View? = null,
    private val errorDrawableRes: Int = R.drawable.ic_broken_image_grey_24dp
) : ImageViewTarget<Drawable>(view) {

    override fun setResource(drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }

    override fun onLoadStarted(placeholder: Drawable?) {
        progress?.isVisible = true
        super.onLoadStarted(placeholder)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        progress?.isVisible = false
        view.scaleType = ScaleType.CENTER

        val vector = AppCompatResources.getDrawable(view.context, errorDrawableRes)
        vector?.setTint(0xFFFFFFFF.toInt())
        view.setImageDrawable(vector)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        progress?.isVisible = false
        super.onLoadCleared(placeholder)
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        progress?.isVisible = false
        view.scaleType = ScaleType.CENTER
        super.onResourceReady(resource, transition)
    }
}
