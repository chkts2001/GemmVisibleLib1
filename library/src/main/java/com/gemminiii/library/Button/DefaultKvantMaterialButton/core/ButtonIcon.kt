package com.gemminiii.library.Button.DefaultKvantMaterialButton.core

import android.graphics.drawable.Drawable
import com.google.android.material.button.MaterialButton

interface ButtonIcon {
    fun applyIcon(
        button: MaterialButton,
        icon: Drawable?,
        position: IconPosition,
    )

    fun removeIcon(button: MaterialButton)

    enum class IconPosition {
        START, END, CENTER
    }
}