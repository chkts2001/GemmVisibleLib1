package com.gemminiii.library.Button.DefaultKvantMaterialButton.core

import android.content.res.ColorStateList

interface ButtonState {
    fun getStateColors(): ColorStateList
    fun getStateAlpha(): FloatArray
    fun getPressedScale(): Float
}