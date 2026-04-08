package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.graphics.Typeface
import android.util.Log
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

interface ButtonConfigurable<T>{
    fun sWidth(width: Int): T
    fun sHeight(height: Int): T
    fun sCornerRadius(radius: Float): T
    fun sBackgroundColor(color: Int): T
    fun sPaddings(padding: Int): T
    fun sStroke(width: Int, color: Int): T
    fun sIcon(iconRes: Int?, iconSize: Int = 25, iconTint: Int = android.R.color.white): T
    fun sIconGravity(position: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER): T
    fun sText(text: String? = null, size: Int? = 14, color: Int? = android.R.color.black, tf: Typeface? = null): T
}
abstract class AbstractButtonConfigurable<T: AbstractButtonConfigurable<T>>: ButtonConfigurable<T>{
    var config = ButtonConfig()

    override fun sWidth(width: Int): T{
        config.width = width
        return this as T
    }
    override fun sHeight(height: Int): T {
        config.height = height
        return this as T
    }

    override fun sCornerRadius(radius: Float): T {
        config.cornerRadius = radius
        return this as T
    }

    override fun sBackgroundColor(color: Int): T {
        config.backgroundColor = color
        return this as T
    }

    override fun sPaddings(padding: Int): T {
        config.padding = padding + config.strokeWidth
        return this as T
    }

    override fun sStroke(width: Int, color: Int): T {
        config.strokeWidth = width
        config.strokeColor = color
        config.padding += config.strokeWidth
        return this as T
    }

    override fun sIcon(iconRes: Int?, iconSize: Int, iconTint: Int): T {
        config.iconRes = iconRes
        config.iconSize = iconSize
        config.iconTint = iconTint
        return this as T
    }

    override fun sIconGravity(position: ButtonIcon.IconPosition): T {
        config.iconGravity = position
        return this as T
    }

    override fun sText(text: String?, size: Int?, color: Int? , tf: Typeface?): T {
        config.text = text
        config.textSize = size ?: 14
        config.textColor = color ?: android.R.color.black
        config.textTypeface = tf
        return this as T
    }
    protected fun applyToButton(button: DefaultMaterialButton) {
        button.applyStyles(config)
    }

    fun getCurrentConfig(): ButtonConfig = config.copy()
    fun setCurrentConfig(newConfig: ButtonConfig): T {
        this.config = newConfig.copy()  // copy() чтобы не было ссылок на один объект
        return this as T
    }
}


//    fun sTextColor(color: Int): T
//    fun sTextSize(size: Int): T
//    fun sTypeface(typeface: Typeface): T