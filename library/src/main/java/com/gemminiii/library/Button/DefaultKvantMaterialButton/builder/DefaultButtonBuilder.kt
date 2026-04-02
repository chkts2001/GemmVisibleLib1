package com.gemminiii.library.Button.DefaultKvantMaterialButton.builder

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.R

class DefaultButtonBuilder(private val context: Context) {
    private var cornerRadius: Float = 0f
    private var backgroundColor: Int = Color.TRANSPARENT
    private var gradientStartColor: Int = Color.TRANSPARENT
    private var gradientEndColor: Int = Color.TRANSPARENT
    private var strokeWidth: Int = 0
    private var strokeColor: Int = Color.TRANSPARENT

    private var icon: Drawable? = null
    private var iconPosition: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER
    private var iconTint: ColorStateList = ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.white))
    private var iconSize: Int = 20
    private var iconPadding: Int = 8

    private var text: String? = null
    private var textColor: Int = Color.WHITE
    private var textSize: Int = 12
    private var typeface: Typeface? = null

    fun setCornerRadius(radius: Float) = apply { this.cornerRadius = radius }
    fun setBackgroundColor(color: Int) = apply { this.backgroundColor = color }
    fun setGradient(startColor: Int, endColor: Int) = apply {
        this.gradientStartColor = startColor
        this.gradientEndColor = endColor
    }
    fun setStroke(width: Int, color: Int) = apply {
        this.strokeWidth = width
        this.strokeColor = color
    }
    fun setIcon(iconRes: Int, iconSize: Int, iconTint: Int = android.R.color.white) = apply {
        this.icon = ContextCompat.getDrawable(context, iconRes)
        this.iconSize = iconSize
        this.iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, iconTint))
    }
    fun setIconGravity(position: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER) = apply {
        this.iconPosition = position
    }
    fun setText(text: String) = apply { this.text = text }
    fun setTextColor(color: Int) = apply { this.textColor = color }
    fun setTextSize(size: Int) = apply { this.textSize = size }
    fun setTypeface(typeface: Typeface) = apply { this.typeface = typeface }

    fun build(): DefaultMaterialButton {
        return DefaultMaterialButton(context).apply {
            setCornerRadius(cornerRadius)
            if (gradientStartColor != Color.TRANSPARENT && gradientEndColor != Color.TRANSPARENT) {
                setGradientColors(gradientStartColor, gradientEndColor)
            } else if (backgroundColor != Color.TRANSPARENT) {
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, backgroundColor))
            }
            setIcon(icon, iconSize, iconTint, iconPosition)
            text = this@DefaultButtonBuilder.text
            setTextColor(ColorStateList.valueOf(textColor))
            setTextSize(textSize)
            typeface?.let { setTypeface(it) }
        }
    }

}