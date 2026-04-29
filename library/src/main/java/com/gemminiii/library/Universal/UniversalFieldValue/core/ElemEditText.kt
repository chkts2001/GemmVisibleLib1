package com.gemminiii.library.Universal.UniversalFieldValue.core

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.ActionBar.LayoutParams
import com.gemminiii.library.Universal.UniversalFieldValue.config.DrawableConfig

interface ElemEditText {
    fun createElemEditText(
        context: Context,
        drawableConfig: DrawableConfig,
        elemConfig: TextElemConfig
    ): EditText
    fun elemEditTextUpdateSize(
        width: Int = LayoutParams.WRAP_CONTENT,
        height: Int = LayoutParams.WRAP_CONTENT,
        margin: Int = 0,
        weight: Float = 0f
    )
}