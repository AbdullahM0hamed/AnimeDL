package com.anime.dl.ui.browse.extension

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.anime.dl.App
import com.anime.dl.databinding.ExtensionCardItemBinding
import com.anime.dl.extensions.models.Extension
import com.anime.dl.R
import com.kennyc.textdrawable.ColorGenerator
import com.kennyc.textdrawable.TextDrawable
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import kotlin.math.min

data class ExtensionItem(val extension: Extension) : 
    AbstractBindingItem<ExtensionCardItemBinding>() {

    override val type: Int = R.id.fastadapter_extension_item_id

    override fun bindView(
        binding: ExtensionCardItemBinding, 
        payloads: List<Any>
    ) {
        binding.extTitle.text = extension.name
        binding.version.text = "${extension.versionName}.${extension.versionCode}"

        val context = App.applicationContext()

        if (extension.isTutorial) {
            binding.image.roundTextIcon(context.getString(R.string.tutorial_extension))
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ExtensionCardItemBinding {
        return ExtensionCardItemBinding.inflate(inflater, parent, false)
    }

    // Used for tutorial extension/source
    fun ImageView.roundTextIcon(text: String) {
        val letter = text.take(1).toUpperCase()
        val size = min(this.width, this.height)

        setImageDrawable(TextDrawable(
            shape = TextDrawable.DRAWABLE_SHAPE_OVAL,
            desiredWidth = size,
            desiredHeight = size,
            typeFace = Typeface.DEFAULT,
            textColor = Color.WHITE,
            text = letter,
            color = ColorGenerator.MATERIAL.getColor(letter)
        ))
    }
}
