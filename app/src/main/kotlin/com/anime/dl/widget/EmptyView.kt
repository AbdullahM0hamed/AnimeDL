package com.anime.dl.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.anime.dl.databinding.CommonViewEmptyBinding

class EmptyView @JvmOverloads constructor(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    init {
        val binding = CommonViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)
        binding.monkeFaceOne.start()
        binding.monkeFaceTwo.start()
        binding.monkeFaceThree.start()
        binding.textLabel.text = "test"
    }
}
