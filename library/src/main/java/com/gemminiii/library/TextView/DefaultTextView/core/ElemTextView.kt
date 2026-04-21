package com.gemminiii.library.TextView.DefaultTextView.core

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig

interface ElemTextView {
    fun textViewCreate(width: Int,
                       height: Int,
                       drawable: Drawable,
                       config: TextViewConfig
                       ): TextView
}