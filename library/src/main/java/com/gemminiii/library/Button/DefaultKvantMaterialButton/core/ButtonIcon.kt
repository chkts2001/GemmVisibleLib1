package com.gemminiii.library.Button.DefaultKvantMaterialButton.core

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import com.google.android.material.button.MaterialButton

interface ButtonIcon {
    fun applyIcon(
        button: MaterialButton,
        icon: Drawable?,
        size: Int,
        tint: ColorStateList,
        position: IconPosition,
    )

    fun removeIcon(button: MaterialButton)

    enum class IconPosition {
        START, END, CENTER
    }
}