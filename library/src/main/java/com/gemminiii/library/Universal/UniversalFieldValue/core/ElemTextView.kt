package com.gemminiii.library.Universal.UniversalFieldValue.core

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import com.gemminiii.library.Universal.UniversalFieldValue.config.DrawableConfig
import com.gemminiii.library.Universal.UniversalFieldValue.config.TextElemConfig
import kotlin.Int

interface ElemTextView {
    fun textViewCreate(
        context: Context,
        drawableConfig : DrawableConfig = DrawableConfig(),
        config: TextElemConfig = TextElemConfig(),
        gravity: Int = Gravity.START
       ): TextView

    fun textViewUpdateSize(
        width: Int = 0,
        height: Int = 0,
        margin: Int = 0,
        weight: Float = 0f
    )
}