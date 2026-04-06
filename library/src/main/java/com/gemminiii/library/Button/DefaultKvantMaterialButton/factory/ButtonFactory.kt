package com.gemminiii.library.Button.DefaultKvantMaterialButton.factory

import android.content.Context
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder
import com.gemminiii.library.R

class ButtonFactory {
    fun createPrimaryButton(context: Context, text: String): DefaultMaterialButton {
        return DefaultButtonBuilder(context)
            .sText(text, 16, R.color.white)
            .sCornerRadius(12f)
            .build()
    }

    fun createSecondaryButton(context: Context, text: String): DefaultMaterialButton {
        return DefaultButtonBuilder(context)
            .sText(text, 14, R.color.black)
            .sBackgroundColor(ContextCompat.getColor(context, R.color.white))
            .sCornerRadius(8f)
            .sStroke(2, ContextCompat.getColor(context, R.color.black))
            .build()
    }

    fun createIconButton(context: Context, iconRes: Int, iconSize: Int, iconTint: Int ): DefaultMaterialButton {
        return DefaultButtonBuilder(context)
            .sIcon(iconRes, iconSize, iconTint)
            .sCornerRadius(24f)
            .sBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            .build()
    }
}