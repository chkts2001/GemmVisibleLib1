package com.gemminiii.library.Button.DefaultKvantMaterialButton.builder

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

class CustomButtonBuilder(private val context: Context) {
    private var cornerRadius: Float = 0f
    private var backgroundColor: Int = Color.TRANSPARENT
    private var gradientStartColor: Int = Color.TRANSPARENT
    private var gradientEndColor: Int = Color.TRANSPARENT
    private var strokeWidth: Int = 0
    private var strokeColor: Int = Color.TRANSPARENT

    private var icon: Drawable? = null
    private var iconPosition: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER
    private var iconPadding: Int = 8

    private var text: String? = null
    private var textColor: Int = Color.WHITE
    private var textSize: Float = 14f
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
    fun setIcon(iconRes: Int, position: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER) = apply {
        this.icon = ContextCompat.getDrawable(context, iconRes)
        this.iconPosition = position
    }
    fun setText(text: String) = apply { this.text = text }
    fun setTextColor(color: Int) = apply { this.textColor = color }
    fun setTextSize(size: Float) = apply { this.textSize = size }
    fun setTypeface(typeface: Typeface) = apply { this.typeface = typeface }

    fun build(): DefaultMaterialButton {
        return DefaultMaterialButton(context).apply {
            setCornerRadius(cornerRadius)
            if (gradientStartColor != Color.TRANSPARENT && gradientEndColor != Color.TRANSPARENT) {
                setGradientColors(gradientStartColor, gradientEndColor)
            } else if (backgroundColor != Color.TRANSPARENT) {
                setBackgroundColor(backgroundColor)
            }
            setIcon(icon, iconPosition)
            text = this@CustomButtonBuilder.text
            setTextColor(ColorStateList.valueOf(textColor))
            setTextSize(textSize)
            typeface?.let { setTypeface(it) }
        }
    }

}