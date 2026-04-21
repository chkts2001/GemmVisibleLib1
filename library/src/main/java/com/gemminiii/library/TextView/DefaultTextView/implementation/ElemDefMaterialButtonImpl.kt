package com.gemminiii.library.TextView.DefaultTextView.implementation

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig
import com.gemminiii.library.TextView.DefaultTextView.core.ElemDefMaterialButton

class ElemDefMaterialButtonImpl(private val antiAlias: Boolean = true): ElemDefMaterialButton {
    lateinit var leftButton: DefaultMaterialButton
    lateinit var rightButton: DefaultMaterialButton

    override fun elemDefMaterialButtonCreate(
        context: Context,
        iconResLeft: Int?,
        iconResRight: Int?,
        buttonConfig: ButtonConfig
    ): Pair<DefaultMaterialButton, DefaultMaterialButton> {
        leftButton = DefaultMaterialButton(context).apply {
            buttonConfig.iconRes = iconResLeft
            buttonConfig.iconGravity = ButtonIcon.IconPosition.CENTER
            applyStyles(buttonConfig)
        }

        rightButton = DefaultMaterialButton(context).apply {
            buttonConfig.iconRes = iconResRight
            buttonConfig.iconGravity = ButtonIcon.IconPosition.CENTER
            applyStyles(buttonConfig)
        }

        return Pair(leftButton, rightButton)
    }

    override fun elemDefMaterialButtonUpdateSize(width: Int, height: Int, margin: Int) {
        leftButton.layoutParams = LinearLayout.LayoutParams(height,
            LinearLayout.LayoutParams.MATCH_PARENT).apply { setMargins(margin / 2, margin, margin, margin) }
        rightButton.layoutParams = LinearLayout.LayoutParams(height,
            LinearLayout.LayoutParams.MATCH_PARENT).apply { setMargins(margin, margin, margin / 2, margin) }
    }

    fun setLeftButtonClickListener(listener: (android.view.View) -> Unit) {
        leftButton.setOnClickListener { listener(it) }
    }

    fun setRightButtonClickListener(listener: (android.view.View) -> Unit) {
        rightButton.setOnClickListener { listener(it) }
    }

    fun setBothButtonsClickListeners(
        leftListener: (android.view.View) -> Unit,
        rightListener: (android.view.View) -> Unit
    ) {
        leftButton.setOnClickListener { leftListener(it) }
        rightButton.setOnClickListener { rightListener(it) }
    }
}