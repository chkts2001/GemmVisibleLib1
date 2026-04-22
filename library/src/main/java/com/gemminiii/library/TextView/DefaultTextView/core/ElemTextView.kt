package com.gemminiii.library.TextView.DefaultTextView.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.TextView
import com.gemminiii.library.TextView.DefaultTextView.config.CommonConfig
import com.gemminiii.library.TextView.DefaultTextView.config.DrawableConfig
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig

interface ElemTextView {
    fun textViewCreate(
        context: Context,
        drawableConfig : DrawableConfig = DrawableConfig(),
        config: TextViewConfig = TextViewConfig()
       ): TextView

    fun textViewUpdateSize(
        width: Int = 0,
        height: Int = 0,
        margin: Int = 0
    )
}