package com.gemminiii.library.TextView.DefaultTextView.core

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import com.gemminiii.library.TextView.DefaultTextView.config.CommonConfig

interface TopElemsLayout {
    fun topElemsLinearCreate(width: Int,
                             height: Int,
                             drawable: Drawable,
                             config: CommonConfig
                             ): LinearLayout
}