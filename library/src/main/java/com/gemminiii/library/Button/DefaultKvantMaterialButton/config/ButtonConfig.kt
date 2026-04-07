package com.gemminiii.library.Button.DefaultKvantMaterialButton.config

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

data class ButtonConfig(
    var width: Int = LinearLayout.LayoutParams.MATCH_PARENT,
    var height: Int = 40,
    var cornerRadius: Float = 30f,
    var backgroundColor: Int? = null,
    var padding: Int = 16,
    var strokeWidth: Int = 0,
    var strokeColor: Int = 0,
    var iconRes: Int? = null,
    var iconSize: Int = 24,
    var iconTint: Int = android.R.color.white,
    var iconGravity: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER,
    var text: String? = null,
    var textSize: Int = 14,
    var textColor: Int = android.R.color.black,
    var textTypeface: Typeface? = null
) {
    fun copyWith(block: ButtonConfig.() -> Unit): ButtonConfig{
        val result = this.copy()
        result.block()
        return result
    }
}