package com.gemminiii.library.Universal.UniversalFieldValue.implementation

import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import com.gemminiii.library.Universal.UniversalFieldValue.config.DrawableConfig
import com.gemminiii.library.Universal.UniversalFieldValue.core.ElemEditText

class ElemEditTextImpl(): ElemEditText {
    lateinit var editText: EditText
    override fun createElemEditText(
        context: Context,
        drawableConfig: DrawableConfig,
        elemConfig: TextElemConfig
    ): EditText {
        editText = EditText(context).apply {

        }
        return editText
    }

    override fun elemEditTextUpdateSize(width: Int, height: Int, margin: Int, weight: Float) {
        editText.layoutParams = LinearLayout.LayoutParams(width, height, weight).apply {
            setMargins(margin,margin, margin, margin)
        }
    }
}