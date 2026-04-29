package com.gemminiii.library.Universal.UniversalFieldValue.core

import android.content.Context
import android.widget.ProgressBar
import com.gemminiii.library.Universal.UniversalFieldValue.config.DrawableConfig
import com.gemminiii.library.Universal.UniversalFieldValue.config.ProgressBarConfig

interface ElemHorizontalProgressBar {
    fun horizontalProgressBarCreate(
        context: Context,
        drawable: DrawableConfig,
        config: ProgressBarConfig
    ): ProgressBar

    fun horizontalProgressBarUpdateSize(
        width: Int,
        height: Int,
        margin: Int
    )
}