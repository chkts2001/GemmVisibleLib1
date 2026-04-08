package com.gemminiii.library.Button.DefaultKvantMaterialButton.factory

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.R

class ButtonFactory private constructor() {

    private var currentBuilder: DefaultButtonBuilder? = null

    fun blackDefaultButton(context: Context, text: String? = null, icon: Int? = null): DefaultButtonBuilder {
        // СОЗДАЁМ НОВЫЙ БИЛДЕР КАЖДЫЙ РАЗ
        val builder = DefaultButtonBuilder(context)
            .sWidth(if(text == null) 40 else ViewGroup.LayoutParams.MATCH_PARENT)
            .sHeight(40)
            .sBackgroundColor(android.R.color.black)
            .sText(text, 14, android.R.color.white, Typeface.DEFAULT_BOLD)
            .sIcon(icon, 25, android.R.color.white)
            .sIconGravity(if(text == null) ButtonIcon.IconPosition.CENTER else ButtonIcon.IconPosition.END)

        currentBuilder = builder
        return builder
    }

    fun applyTo(button: DefaultMaterialButton) {
        currentBuilder?.applyTo(button)
        currentBuilder = null
    }

    companion object {
        @Volatile
        private var instance: ButtonFactory? = null

        fun getInstance(): ButtonFactory {
            return instance ?: synchronized(this) {
                instance ?: ButtonFactory().also { instance = it }
            }
        }
    }
}