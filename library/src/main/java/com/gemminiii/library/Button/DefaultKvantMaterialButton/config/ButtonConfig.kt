package com.gemminiii.library.Button.DefaultKvantMaterialButton.config

import android.graphics.Typeface
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon

data class ButtonConfig(
    var width: Int = LinearLayout.LayoutParams.MATCH_PARENT,
    var height: Int = 40,
    var iconRes: Int? = null,
    var iconSize: Int = 24,
    var iconTint: Int = android.R.color.white,
    var iconGravity: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER,
    var text: String? = null,
    var textSize: Int = 14,
    var textColor: Int = android.R.color.black,
    var textTypeface: Typeface? = null,
    var insetTop: Int = 0,
    var insetBottom: Int = 0
) {
    fun copyWith(block: ButtonConfig.() -> Unit): ButtonConfig{
        val result = this.copy()
        result.block()
        return result
    }
}