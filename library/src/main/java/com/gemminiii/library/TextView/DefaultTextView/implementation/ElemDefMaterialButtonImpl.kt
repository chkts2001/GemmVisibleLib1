package com.gemminiii.library.TextView.DefaultTextView.implementation

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonDrawableConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums
import com.gemminiii.library.TextView.DefaultTextView.core.ElemDefMaterialButton

class ElemDefMaterialButtonImpl(private val antiAlias: Boolean = true): ElemDefMaterialButton {
    lateinit var leftButton: DefaultMaterialButton
    lateinit var rightButton: DefaultMaterialButton

    override fun elemDefMaterialButtonCreate(
        context: Context,
        iconResLeft: Int?,
        iconResRight: Int?,
        modeVisibleBtn: CommonEnums.ButtonVisibilityMode,
        buttonDrawConfig: ButtonDrawableConfig,
        buttonConfig: ButtonConfig
    ): Pair<DefaultMaterialButton, DefaultMaterialButton> {
        leftButton = DefaultMaterialButton(context).apply {
            buttonConfig.iconRes = iconResLeft
            buttonConfig.iconGravity = ButtonIcon.IconPosition.CENTER
            applyStyles(buttonDrawConfig, buttonConfig)
        }
        leftButton.visibility = if(modeVisibleBtn == CommonEnums.ButtonVisibilityMode.LEFT || modeVisibleBtn == CommonEnums.ButtonVisibilityMode.ALL) View.VISIBLE else View.GONE

        rightButton = DefaultMaterialButton(context).apply {
            buttonConfig.iconRes = iconResRight
            buttonConfig.iconGravity = ButtonIcon.IconPosition.CENTER
            applyStyles(buttonDrawConfig, buttonConfig)
        }
        rightButton.visibility = if(modeVisibleBtn == CommonEnums.ButtonVisibilityMode.RIGHT || modeVisibleBtn == CommonEnums.ButtonVisibilityMode.ALL) View.VISIBLE else View.GONE

        return Pair(leftButton, rightButton)
    }

    override fun elemDefMaterialButtonUpdateSize(width: Int, height: Int, margin: Int) {
        leftButton.layoutParams = LinearLayout.LayoutParams(width,
            height).apply { setMargins(margin, margin, margin, margin) }
        rightButton.layoutParams = LinearLayout.LayoutParams(width,
            height).apply { setMargins(margin, margin, margin, margin) }
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