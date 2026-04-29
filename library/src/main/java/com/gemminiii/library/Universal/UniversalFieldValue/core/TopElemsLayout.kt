package com.gemminiii.library.Universal.UniversalFieldValue.core

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import com.gemminiii.library.Universal.UniversalFieldValue.config.CommonConfig

interface TopElemsLayout {
    fun topElemsLinearCreate(width: Int,
                             height: Int,
                             drawable: Drawable,
                             config: CommonConfig
                             ): LinearLayout
}