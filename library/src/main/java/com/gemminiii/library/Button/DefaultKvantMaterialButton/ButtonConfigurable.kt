package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.graphics.Typeface
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

interface ButtonConfigurable<T: ButtonConfigurable<T>> {
    fun sCornerRadius(radius: Float): T
    fun sBackgroundColor(color: Int): T
    fun sGradient(startColor: Int, endColor: Int): T

    fun sPaddings(padding: Int): T
    fun sStroke(width: Int, color: Int): T
    fun sIcon(iconRes: Int, iconSize: Int, iconTint: Int = android.R.color.white): T
    fun sIconGravity(position: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER): T
    fun sText(text: String): T
    fun sTextColor(color: Int): T
    fun sTextSize(size: Int): T
    fun sTypeface(typeface: Typeface): T
}