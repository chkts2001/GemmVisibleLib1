package com.gemminiii.library.Button.DefaultKvantMaterialButton.builder

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.ButtonConfigurable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

class DefaultButtonBuilder(private val context: Context): ButtonConfigurable<DefaultButtonBuilder> {
    private var cornerRadius: Float = 0f
    private var backgroundColor: Int = Color.TRANSPARENT
    private var gradientStartColor: Int = Color.TRANSPARENT
    private var gradientEndColor: Int = Color.TRANSPARENT
    private var strokeWidth: Int = 0
    private var strokeColor: Int = Color.TRANSPARENT
    private var buttonPadding = 6

    private var icon: Drawable? = null
    private var iconPosition: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER
    private var iconTint: ColorStateList = ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.white))
    private var iconSize: Int = 20
    private var iconPadding: Int = 8

    private var text: String? = null
    private var textColor: Int = Color.WHITE
    private var textSize: Int = 12
    private var typeface: Typeface? = null

    override fun sCornerRadius(radius: Float): DefaultButtonBuilder = apply { this.cornerRadius = radius }
    override fun sBackgroundColor(color: Int): DefaultButtonBuilder = apply { this.backgroundColor = color }
    override fun sGradient(startColor: Int, endColor: Int): DefaultButtonBuilder = apply {
        this.gradientStartColor = startColor
        this.gradientEndColor = endColor
    }

    override fun sPaddings(padding: Int): DefaultButtonBuilder = apply{ this.buttonPadding = padding }
    override fun sStroke(width: Int, color: Int): DefaultButtonBuilder = apply {
        this.strokeWidth = width
        this.strokeColor = color
    }
    override fun sIcon(iconRes: Int, iconSize: Int, iconTint: Int): DefaultButtonBuilder = apply {
        this.icon = ContextCompat.getDrawable(context, iconRes)
        this.iconSize = iconSize
        this.iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, iconTint))
    }
    override fun sIconGravity(position: ButtonIcon.IconPosition): DefaultButtonBuilder = apply {
        this.iconPosition = position
    }
    override fun sText(text: String): DefaultButtonBuilder = apply { this.text = text }
    override fun sTextColor(color: Int): DefaultButtonBuilder = apply { this.textColor = color }
    override fun sTextSize(size: Int): DefaultButtonBuilder = apply { this.textSize = size }
    override fun sTypeface(typeface: Typeface): DefaultButtonBuilder = apply { this.typeface = typeface }
//    override fun sChange(): DefaultButtonBuilder = apply {}

    fun build(): DefaultMaterialButton {
        return DefaultMaterialButton(context).apply {
            setCornerRadius(cornerRadius)
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, backgroundColor))
            setIcon(icon, iconSize, iconTint, iconPosition)
            text = this@DefaultButtonBuilder.text
            setTextColor(ColorStateList.valueOf(textColor))
            setTextSize(textSize)
            typeface?.let { sTypeface(it) }
        }
    }

}