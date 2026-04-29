package com.gemminiii.library.Universal.UniversalFieldValue.implementation

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.Log
import com.gemminiii.library.Universal.UniversalFieldValue.core.sDrawable

class sDrawableImpl: sDrawable {
    override fun createDrawable(
        cornerRadius: Float?,
        backgroundColor: Int?,
        strokeWidth: Int?,
        strokeColor: Int?
    ): Drawable {
        val mainDrawable = GradientDrawable().apply {
            if (cornerRadius != null) this.cornerRadius = cornerRadius
            if (backgroundColor != null) setColor(backgroundColor)
            if (strokeWidth != null && strokeColor != null && strokeWidth > 0) {
                Log.d("Builder", "s_width: ${strokeWidth} s_color: ${strokeColor}")
                setStroke(strokeWidth, strokeColor)
            }
        }

        // Создаем ripple эффект
        val rippleColor = ColorStateList.valueOf(Color.argb(60, 0, 0, 0)) // Увеличил прозрачность
        val rippleDrawable = RippleDrawable(rippleColor, mainDrawable, null)

        return rippleDrawable
    }
}