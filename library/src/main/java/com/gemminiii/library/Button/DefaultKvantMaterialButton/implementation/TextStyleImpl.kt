package com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.widget.Button
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonText
import com.google.android.material.button.MaterialButton

class TextStyleImpl : ButtonText {
    override fun applyTextStyle(
        button: MaterialButton,
        text: String?,
        textColor: ColorStateList?,
        textSize: Float,
        typeface: Typeface?
    ) {
        text?.let { button.text = it }
        textColor?.let { button.setTextColor(it) }
        if (textSize > 0) button.textSize = textSize
        typeface?.let { button.typeface = it }
    }

    override fun updateTextColor(
        button: Button,
        colorStateList: ColorStateList
    ) {
        button.setTextColor(colorStateList)
    }

    override fun updateTextSize(button: Button, size: Float) {
        button.textSize = size
    }
}