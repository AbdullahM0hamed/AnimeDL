package com.anime.dl.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.anime.dl.databinding.CommonViewEmptyBinding

class CommonViewEmpty : FrameLayout(
    context: Context,
    atrrs: AttributeSet? = null
) {
    private lateinit var binding

    init {
        binding = CommonViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)
        binding.monkeFace.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        binding.monkeFace.start()
    }

    public fun setText(text: String) {
        binding.textLabel.text = text
    }
}
