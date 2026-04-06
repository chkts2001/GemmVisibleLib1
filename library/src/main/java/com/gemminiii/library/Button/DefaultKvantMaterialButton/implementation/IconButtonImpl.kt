package com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.google.android.material.button.MaterialButton

class IconButtonImpl : ButtonIcon {
    override fun applyIcon(
        button: MaterialButton,
        icon: Drawable?,
        size: Int,
        tint: ColorStateList?,
        position: ButtonIcon.IconPosition
    ) {
        icon?.let {
            button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            button.icon = it
            button.compoundDrawablePadding = 0
            Log.d("_lib_", "inside: $size")
            button.iconSize = size
            button.iconTint = tint
            it.setBounds(0, 0, size, size)
            when (position) {
                ButtonIcon.IconPosition.START -> {
                    button.iconGravity = MaterialButton.ICON_GRAVITY_START
                    button.gravity = Gravity.CENTER
                }
                ButtonIcon.IconPosition.END -> {
                    button.iconGravity = MaterialButton.ICON_GRAVITY_END
                    button.gravity = Gravity.CENTER
                }
                ButtonIcon.IconPosition.CENTER -> {
                    button.tag = button.text.toString()
                    button.text = null
                    button.gravity = Gravity.CENTER
                    button.iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
                }
            }
        }
    }

    override fun removeIcon(button: MaterialButton) {
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        val originalText = button.tag as? CharSequence
        if (originalText != null) {
            button.text = originalText
            button.tag = null
        }
        button.gravity = Gravity.CENTER_VERTICAL or Gravity.START
    }
}