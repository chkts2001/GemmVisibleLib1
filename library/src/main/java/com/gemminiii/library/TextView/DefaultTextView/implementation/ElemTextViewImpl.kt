package com.gemminiii.library.TextView.DefaultTextView.implementation

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.Log
import android.widget.TextView
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig
import com.gemminiii.library.TextView.DefaultTextView.core.ElemTextView

class ElemTextViewImpl(private val antiAlias: Boolean = true): ElemTextView {
    override fun textViewCreate(
        width: Int,
        height: Int,
        drawable: Drawable,
        config: TextViewConfig
    ): TextView {
        TODO("Not yet implemented")
    }
}