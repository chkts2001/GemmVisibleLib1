package com.gemminiii.library.TextView.DefaultTextView.core

import android.graphics.drawable.Drawable

interface sDrawable {
    fun createDrawable(cornerRadius: Float?,
       backgroundColor: Int?,
       strokeWidth: Int?,
       strokeColor: Int?
    ): Drawable
}