package com.gemminiii.library.Universal.UniversalFieldValue.implementation

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.gemminiii.library.Universal.UniversalFieldValue.config.DrawableConfig
import com.gemminiii.library.Universal.UniversalFieldValue.config.ProgressBarConfig
import com.gemminiii.library.Universal.UniversalFieldValue.core.ElemHorizontalProgressBar

class ElemHorizontalProgressBarImpl(private val antiAlias: Boolean = true):
    ElemHorizontalProgressBar {
    lateinit var progressBar: ProgressBar
    override fun horizontalProgressBarCreate(
        context: Context,
        drawable: DrawableConfig,
        config: ProgressBarConfig
    ): ProgressBar {
        progressBar = ProgressBar(context, null, R.attr.progressBarStyleHorizontal).apply {
            config.apply {
                scaleY = 3f
                isIndeterminate = true
                indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(context, bottomProgressTrackColor))
                progressBackgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, bottomProgressBackgroundColor))
            }
        }
        return progressBar
    }

    override fun horizontalProgressBarUpdateSize(
        width: Int,
        height: Int,
        margin: Int
    ) {
        progressBar.layoutParams = LinearLayout.LayoutParams(width, height).apply {
            setMargins(margin,margin, margin, margin)
        }
    }

}