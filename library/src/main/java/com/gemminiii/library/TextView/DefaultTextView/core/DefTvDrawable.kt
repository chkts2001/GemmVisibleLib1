package com.gemminiii.library.TextView.DefaultTextView.core

import android.graphics.drawable.Drawable

interface DefTvDrawable {
    fun createBackground(
        cornerRadius: Float?,
        backgroundColor: Int?,
        strokeWidth: Int?,
        strokeColor: Int?
    ): Drawable
}