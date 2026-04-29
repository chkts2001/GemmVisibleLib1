package com.gemminiii.library.Universal.UniversalFieldValue.core

import android.graphics.drawable.Drawable

interface sDrawable {
    fun createDrawable(cornerRadius: Float?,
       backgroundColor: Int?,
       strokeWidth: Int?,
       strokeColor: Int?
    ): Drawable
}