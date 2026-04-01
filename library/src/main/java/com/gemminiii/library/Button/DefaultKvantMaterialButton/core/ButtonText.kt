package com.gemminiii.library.Button.DefaultKvantMaterialButton.core

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.widget.Button
import com.google.android.material.button.MaterialButton

interface ButtonText {
    fun applyTextStyle(
        button: MaterialButton,
        text: String?,
        textColor: ColorStateList?,
        textSize: Float,
        typeface: Typeface?
    )
    fun updateTextColor(button: Button, colorStateList: ColorStateList)
    fun updateTextSize(button: Button, size: Float)
}