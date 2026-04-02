package com.gemminiii.library.Button.DefaultKvantMaterialButton.factory

import android.content.Context
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder
import com.gemminiii.library.R

class ButtonFactory {
    fun createPrimaryButton(context: Context, text: String): DefaultMaterialButton {
        return DefaultButtonBuilder(context)
            .setText(text)
            .setCornerRadius(12f)
            .setTextColor(ContextCompat.getColor(context, R.color.white))
            .setTextSize(16)
            .build()
    }

    fun createSecondaryButton(context: Context, text: String): DefaultMaterialButton {
        return DefaultButtonBuilder(context)
            .setText(text)
            .setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            .setCornerRadius(8f)
            .setStroke(2, ContextCompat.getColor(context, R.color.black))
            .setTextColor(ContextCompat.getColor(context, R.color.black))
            .setTextSize(14)
            .build()
    }

    fun createIconButton(context: Context, iconRes: Int, iconSize: Int, iconTint: Int ): DefaultMaterialButton {
        return DefaultButtonBuilder(context)
            .setIcon(iconRes, iconSize, iconTint)
            .setCornerRadius(24f)
            .setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            .build()
    }
}