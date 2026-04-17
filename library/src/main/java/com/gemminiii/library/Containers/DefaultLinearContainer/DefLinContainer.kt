package com.gemminiii.library.Containers.DefaultLinearContainer

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
import com.gemminiii.library.Common.toCommonAttr
import com.gemminiii.library.Containers.DefaultLinearContainer.config.DefLinContainerConfig
import com.gemminiii.library.Containers.DefaultLinearContainer.core.DefLinContainerDrawable
import com.gemminiii.library.Containers.DefaultLinearContainer.implemenatation.DefLinContainerDrawableImpl
import com.gemminiii.library.R

open class DefLinContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {
    private val containerDrawable: DefLinContainerDrawable = DefLinContainerDrawableImpl()
    val containerConfig = DefLinContainerConfig()

    init{
        initAttributes(attrs)
        applyStyles(containerConfig)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(attrs, commonAttrArray).use { typedArray ->
                val commonAttr = context.readCommonAttributes(attrs)
                containerConfig.apply {
                    // Основные параметры
                    cornersRadius = commonAttr.cornersRadius
                    backgroundColor = commonAttr.backgroundColor
                    strokeWidth = commonAttr.strokeWidth
                    strokeColor = commonAttr.strokeColor
                    padding = commonAttr.padding
                }
            }
        }
    }

    fun applyStyles(config: DefLinContainerConfig){
        try{
            config.apply{
                Log.d("DefLinContainer", "Applying styles:")
                Log.d("DefLinContainer", "cornersRadius: $cornersRadius")
                Log.d("DefLinContainer", "backgroundColor: $backgroundColor")
                Log.d("DefLinContainer", "strokeWidth: $strokeWidth")
                Log.d("DefLinContainer", "strokeColor: $strokeColor")
                Log.d("DefLinContainer", "padding: $padding")
                // Создаем фон
                backDrawable = containerDrawable.createBackground(
                    cornersRadius,
                    backgroundColor,
                    strokeWidth,
                    strokeColor
                )

                // Применяем фон
                background = backDrawable

                // Применяем отступы (это важно!)
                if (padding > 0) {
                    setPadding(padding, padding, padding, padding)
                }

                // Принудительно перерисовываем
                requestLayout()
                invalidate()
            }
        }catch(e: Exception){

        }
    }
}