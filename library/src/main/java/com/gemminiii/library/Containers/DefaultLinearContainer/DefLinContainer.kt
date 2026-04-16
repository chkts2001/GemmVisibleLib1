package com.gemminiii.library.Containers.DefaultLinearContainer

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import com.gemminiii.library.Containers.DefaultLinearContainer.config.DefLinContainerConfig
import com.gemminiii.library.Containers.DefaultLinearContainer.core.DefLinContainerDrawable
import com.gemminiii.library.Containers.DefaultLinearContainer.implemenatation.DefLinContainerDrawableImpl
import com.gemminiii.library.R

class DefLinContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {
    private val containerDrawable: DefLinContainerDrawable = DefLinContainerDrawableImpl()
    val containerConfig = DefLinContainerConfig()

    init{
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.DefaultListParam).apply {
                try {
                    containerConfig.apply {
                        // Основные параметры
                        cornersRadius = getDimension(
                            R.styleable.DefaultListParam_sCornerRadius,
                            resources.getDimension(R.dimen.default_corner_radius)
                        )
                        backgroundColor = getColor(
                            R.styleable.DefaultListParam_sBackgroundColor,
                            Color.TRANSPARENT
                        )
                        strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultListParam_sStrokeWidth,
                            0
                        )
                        strokeColor = getColor(
                            R.styleable.DefaultListParam_sStrokeColor,
                            Color.TRANSPARENT
                        )
                        padding = getDimensionPixelSize(
                            R.styleable.DefaultListParam_sPadding,
                            6
                        )
                    }
                } finally {
                    recycle()
                }
            }
        }
    }
}