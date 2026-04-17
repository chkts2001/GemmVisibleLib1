package com.gemminiii.library.Containers.DefaultLinearContainer.config

import android.graphics.drawable.Drawable
import android.widget.LinearLayout

class DefLinContainerConfig {
    var width: Int = LinearLayout.LayoutParams.WRAP_CONTENT
    var height: Int = LinearLayout.LayoutParams.WRAP_CONTENT
    var backDrawable: Drawable? = null
    var cornersRadius: Float = 5f
    var backgroundColor: Int? = android.R.color.transparent
    var strokeWidth: Int = 0
    var strokeColor: Int = android.R.color.black
    var padding: Int = 0
}