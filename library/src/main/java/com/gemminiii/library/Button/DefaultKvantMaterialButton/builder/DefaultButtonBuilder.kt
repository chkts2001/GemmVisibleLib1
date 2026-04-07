package com.gemminiii.library.Button.DefaultKvantMaterialButton.builder

import android.content.Context
import android.util.Log
import android.view.View
import com.gemminiii.library.Button.DefaultKvantMaterialButton.AbstractButtonConfigurable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.ButtonConfigurable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton

class DefaultButtonBuilder(private val context: Context):
    AbstractButtonConfigurable<DefaultButtonBuilder>() {

    fun build(): DefaultMaterialButton {
        return DefaultMaterialButton(context).apply {
            //Log.d("Builder", "Creating button with width=$_width, height=$_height")
            applyToButton(this)
        }
    }

    fun applyTo(button: DefaultMaterialButton) {
        applyToButton(button)
    }
}