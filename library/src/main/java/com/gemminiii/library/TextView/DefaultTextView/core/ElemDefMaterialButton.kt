package com.gemminiii.library.TextView.DefaultTextView.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig

interface ElemDefMaterialButton {
    fun elemDefMaterialButtonCreate(
        context: Context,
        iconResLeft: Int? = null,
        iconResRight: Int? = null,
        buttonConfig: ButtonConfig = ButtonConfig()
    ): Pair<DefaultMaterialButton, DefaultMaterialButton>
    fun elemDefMaterialButtonUpdateSize(
        width: Int,
        height: Int,
        margin: Int
    )
}