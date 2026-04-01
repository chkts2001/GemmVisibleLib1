package com.gemminiii.library.Button.DefaultKvantMaterialButton.core

import android.graphics.drawable.Drawable

interface ButtonDrawable {
    fun createBackground(
        cornerRadius: Float,
        backgroundColor: Int,
        strokeWidth: Int,
        strokeColor: Int
    ): Drawable

    fun updateBackground(
        drawable: Drawable,
        cornerRadius: Float,
        backgroundColor: Int,
        strokeWidth: Int,
        strokeColor: Int
    )
}