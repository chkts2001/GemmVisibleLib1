package com.gemminiii.library.Containers.DefaultLinearContainer.core

import android.graphics.drawable.Drawable

interface DefLinContainerDrawable {
    fun createBackground(
        cornerRadius: Float?,
        backgroundColor: Int?,
        strokeWidth: Int?,
        strokeColor: Int?
    ): Drawable
}