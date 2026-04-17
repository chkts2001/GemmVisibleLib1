package com.gemminiii.library.TextView.DefaultTextView

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
import com.gemminiii.library.Containers.DefaultLinearContainer.DefLinContainer

class DefaultTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): DefLinContainer(context, attrs, defStyleAttr) {
    init{

    }

    private fun initAttributes(attrs: AttributeSet?){
        attrs?.let{
            context.obtainStyledAttributes(attrs, commonAttrArray).use {
                val commonAttr = context.readCommonAttributes(attrs)

            }
        }
    }
    private fun applyStyles(){}
}