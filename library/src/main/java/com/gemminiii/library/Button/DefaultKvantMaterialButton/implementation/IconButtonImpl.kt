package com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.google.android.material.button.MaterialButton

class IconButtonImpl : ButtonIcon {
    override fun applyIcon(
        button: MaterialButton,
        icon: Drawable?,
        size: Int,
        tint: ColorStateList,
        position: ButtonIcon.IconPosition
    ) {
        icon?.let {
            button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            button.icon = it
            button.compoundDrawablePadding = 0
            button.iconSize = size
            button.iconTint = tint
//            when (position) {
//                ButtonIcon.IconPosition.START -> {
//                    button.setCompoundDrawablesWithIntrinsicBounds(
//                        it,
//                        null,
//                        null,
//                        null
//                    )
//                }
//
//                ButtonIcon.IconPosition.END -> {
//                    button.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        it,
//                        null
//                    )
//                    button.gravity = Gravity.CENTER_HORIZONTAL
//                }
//
//                ButtonIcon.IconPosition.CENTER -> {
//                    button.setCompoundDrawablesWithIntrinsicBounds(
//                        it,
//                        null,
//                        null,
//                        null
//                    )
//                    val originalText = button.text
//                    button.text = null
//                    button.gravity = Gravity.CENTER
//                    button.tag = originalText
//                }
//            }
            when (position) {
                ButtonIcon.IconPosition.START -> {
                    button.gravity = Gravity.CENTER_VERTICAL or Gravity.START
                }
                ButtonIcon.IconPosition.END -> {
                    button.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                }
                ButtonIcon.IconPosition.CENTER -> {
                    button.text = null
                    button.gravity = Gravity.CENTER
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