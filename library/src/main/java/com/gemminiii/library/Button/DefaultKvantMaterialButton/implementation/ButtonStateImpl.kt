package com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation

import android.content.res.ColorStateList
import android.graphics.Color
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonState

class ButtonStateImpl(private val normalColor: Int = Color.parseColor("#6200EE"),
                      private val pressedColor: Int = Color.parseColor("#3700B3"),
                      private val disabledColor: Int = Color.parseColor("#CCCCCC"),
                      private val selectedColor: Int = Color.parseColor("#03DAC5")): ButtonState {
    override fun getStateColors(): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_selected),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf()
        )

        val colors = intArrayOf(
            pressedColor,
            selectedColor,
            disabledColor,
            normalColor
        )

        return ColorStateList(states, colors)
    }

    override fun getStateAlpha(): FloatArray {
        return floatArrayOf(
            1.0f,
            1.0f,
            0.5f,
            1.0f
        )
    }

    override fun getPressedScale(): Float = 0.97f
}