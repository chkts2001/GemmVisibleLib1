package com.gemminiii.library.TextView.DefaultTextView.core

import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig

interface ElemHorizontalProgressBar {
    fun horizontalProgressBarCreate(
        width: Int,
        height: Int,
        drawable: Drawable,
        config: TextViewConfig
    ): ProgressBar
}