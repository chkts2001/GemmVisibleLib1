package com.gemminiii.library.TextView.DefaultTextView.implementation

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.TextView.DefaultTextView.config.CommonConfig
import com.gemminiii.library.TextView.DefaultTextView.config.DrawableConfig
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig
import com.gemminiii.library.TextView.DefaultTextView.core.ElemTextView

class ElemTextViewImpl(private val antiAlias: Boolean = true): ElemTextView {
    lateinit var textView: TextView
    override fun textViewCreate(
        context: Context,
        drawableConfig: DrawableConfig,
        config: TextViewConfig
    ): TextView {
        textView = TextView(context).apply {
            config.apply {
                Log.d("Builder", "t: ${textTv} ts: $textSizeTv tc: $textColorTv")
                text = textTv
                textSize = textSizeTv.toFloat()
                setTextColor(ContextCompat.getColor(context, textColorTv))
                typeface = textTypefaceTv
                setPadding(drawableConfig.padding, drawableConfig.padding, drawableConfig.padding, drawableConfig.padding)
                background = GradientDrawable().apply {
                    setStroke(drawableConfig.strokeWidth, ContextCompat.getColor(context,drawableConfig.strokeColor))
                    setColor(ContextCompat.getColor(context, drawableConfig.backgroundColor))
                    cornerRadius = drawableConfig.cornersRadius
                }


            }
        }
        return textView
    }

    override fun textViewUpdateSize(width: Int, height: Int, margin: Int) {
        textView.layoutParams = LinearLayout.LayoutParams(width, height, 1f).apply {
            setMargins(margin,margin, margin, margin)
        }
    }
}