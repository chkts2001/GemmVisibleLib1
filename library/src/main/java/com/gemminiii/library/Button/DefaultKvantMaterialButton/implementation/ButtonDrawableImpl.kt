package com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.util.Log
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonDrawable

class ButtonDrawableImpl(private val antiAlias: Boolean = true) :  ButtonDrawable{
    override fun createBackground(
        cornerRadius: Float?,
        backgroundColor: Int?,
        strokeWidth: Int?,
        strokeColor: Int?,
    ): Drawable {
        val mainDrawable = GradientDrawable().apply {
            if(cornerRadius != null) this.cornerRadius = cornerRadius
            if(backgroundColor != null) setColor(backgroundColor)
            if(strokeWidth != null && strokeColor != null){
                Log.d("Builder", "s_width: ${strokeWidth} s_color: ${strokeColor}")
                setStroke(strokeWidth, strokeColor)
            }
        }
        Log.d("Builder", "=== Drawable Debug ===")
        Log.d("Builder", "cornerRadius: $cornerRadius")
        Log.d("Builder", "backgroundColor: $backgroundColor")
        Log.d("Builder", "strokeWidth: $strokeWidth")
        Log.d("Builder", "strokeColor: $strokeColor")
        Log.d("Builder", "mainDrawable: ${mainDrawable.javaClass.simpleName}")
        try {
            val colorField = GradientDrawable::class.java.getDeclaredField("mSolidColors")
            colorField.isAccessible = true
            Log.d("Builder", "Colors: ${colorField.get(mainDrawable)}")
        } catch (e: Exception) {
            // Игнорируем
        }

        val rippleColor = ColorStateList.valueOf(Color.argb(50, 0, 0, 0))
        val rippleDrawable = RippleDrawable(rippleColor, mainDrawable, null)

        // Проверяем, что ripple не перекрывает фон
        rippleDrawable.setColor(ColorStateList.valueOf(Color.TRANSPARENT))

        return rippleDrawable
    }
}