package com.anime.dl.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.anime.dl.databinding.CommonViewEmptyBinding

class CommonViewEmpty(
    context: Context,
    atrrs: AttributeSet? = null
) : FrameLayout(context, atrrs) {

    lateinit var binding: CommonViewEmptyBinding

    init {
        binding = CommonViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    public fun setText(text: String) {
        binding.textLabel.text = text
    }
}
