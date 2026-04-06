package com.gemminiii.library.Button.DefaultKvantMaterialButton.builder

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.ButtonConfigurable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

class DefaultButtonBuilder(private val context: Context): ButtonConfigurable<DefaultButtonBuilder> {
    private var _height: Int = 0
    private var _width: Int = 0
    private var cornerRadius: Float = 0f
    private var backgroundColor: Int = Color.TRANSPARENT
    private var gradientStartColor: Int = Color.TRANSPARENT
    private var gradientEndColor: Int = Color.TRANSPARENT
    private var strokeWidth: Int = 0
    private var strokeColor: Int = Color.TRANSPARENT
    private var buttonPadding = 6

    private var _iconRes: Int = 0
    private var _iconPosition: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER
    private var _iconTint: Int = android.R.color.white
    private var _iconSize: Int = 20
    private var iconPadding: Int = 8

    private var text: String? = null
    private var textColor: Int = Color.WHITE
    private var textSize: Int = 12
    private var typeface: Typeface? = null
    override fun sWidth(width: Int): DefaultButtonBuilder = apply {
        this._width = width
    }

    override fun sHeight(height: Int): DefaultButtonBuilder = apply{
        this._height = height
    }

    override fun sCornerRadius(radius: Float): DefaultButtonBuilder = apply { this.cornerRadius = radius }
    override fun sBackgroundColor(color: Int): DefaultButtonBuilder = apply { this.backgroundColor = color }
    override fun sGradient(startColor: Int, endColor: Int): DefaultButtonBuilder = apply {
        this.gradientStartColor = startColor
        this.gradientEndColor = endColor
    }

    override fun sPaddings(padding: Int): DefaultButtonBuilder = apply{
        this.buttonPadding = padding + strokeWidth
    }
    override fun sStroke(width: Int, color: Int): DefaultButtonBuilder = apply {
        this.strokeWidth = width
        this.strokeColor = color
        this.buttonPadding = buttonPadding + strokeWidth
    }
    override fun sIcon(iconRes: Int, iconSize: Int, iconTint: Int): DefaultButtonBuilder = apply {
        this._iconRes =  iconRes
        this._iconSize = iconSize
        this._iconTint = iconTint
    }
    override fun sIconGravity(position: ButtonIcon.IconPosition): DefaultButtonBuilder = apply {
        this._iconPosition = position
    }

    override fun sText(
        text: String?,
        size: Int?,
        color: Int?,
        tf: Typeface?
    ): DefaultButtonBuilder = apply {
        if(text != null) this.text = text
        if(size != null) this.textSize = size
        if(color != null) this.textColor = color
        if(tf != null) this.typeface = tf
    }
//    override fun sChange(): DefaultButtonBuilder = apply {}

    fun build(): DefaultMaterialButton {
        return DefaultMaterialButton(context).apply {
            Log.d("Builder", "Creating button with width=$_width, height=$_height")

            setCornerRadius(cornerRadius)

//            val bgColor = try {
//                ContextCompat.getColor(context, backgroundColor)
//            } catch (e: Exception) {
//                backgroundColor
//            }
//            backgroundTintList = ColorStateList.valueOf(bgColor)

            setIcon(_iconRes, _iconSize, _iconTint, _iconPosition)
            text = this@DefaultButtonBuilder.text
            setTextColor(ColorStateList.valueOf(textColor))
            setTextSize(textSize.toFloat())
            typeface = this@DefaultButtonBuilder.typeface

            // Принудительно делаем видимым
            visibility = View.VISIBLE

            setSizeParams(_width, _height)

            Log.d("Builder", "Button created: $this")
            Log.d("Builder", "LayoutParams: ${layoutParams}")
        }
    }
}