package com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonDrawable

class ButtonDrawableImpl(private val antiAlias: Boolean = true) :  ButtonDrawable{
    override fun createBackground(
        cornerRadius: Float,
        backgroundColor: Int,
        strokeWidth: Int,
        strokeColor: Int
    ): Drawable {
        val mainDrawable = GradientDrawable().apply {
            this.cornerRadius = cornerRadius
            setColor(backgroundColor)
            if(strokeWidth > 0 && strokeColor != Color.TRANSPARENT){
                setStroke(strokeWidth, strokeColor)
            }
        }
        val rippleColor = ColorStateList.valueOf(Color.argb(50, 0, 0,0))
        return RippleDrawable(rippleColor, mainDrawable, null)
    }

    override fun updateBackground(
        drawable: Drawable,
        cornerRadius: Float,
        backgroundColor: Int,
        strokeWidth: Int,
        strokeColor: Int
    ) {
        val rippleDrawable = drawable as? RippleDrawable
        val layerDrawable = rippleDrawable?.findDrawableByLayerId(android.R.id.background)

        val gradientDrawable = when {
            layerDrawable is LayerDrawable -> {
                layerDrawable.findDrawableByLayerId(0) as? GradientDrawable
            }
            layerDrawable is GradientDrawable -> layerDrawable
            else -> null
        }

        gradientDrawable?.apply {
            this.cornerRadius = cornerRadius
            setColor(backgroundColor)
            if (strokeWidth > 0 && strokeColor != Color.TRANSPARENT) {
                setStroke(strokeWidth, strokeColor)
            }
        }
    }
}