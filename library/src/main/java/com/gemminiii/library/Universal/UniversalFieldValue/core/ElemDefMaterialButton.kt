package com.gemminiii.library.Universal.UniversalFieldValue.core

import android.content.Context
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonDrawableConfig

interface ElemDefMaterialButton {
    fun elemDefMaterialButtonCreate(
        context: Context,
        iconResLeft: Int? = null,
        iconResRight: Int? = null,
        modeVisibleBtn: CommonEnums.ButtonVisibilityMode = CommonEnums.ButtonVisibilityMode.NONE,
        buttonDrawConfig: ButtonDrawableConfig = ButtonDrawableConfig(),
        buttonConfig: ButtonConfig = ButtonConfig()
    ): Pair<DefaultMaterialButton, DefaultMaterialButton>
    fun elemDefMaterialButtonUpdateSize(
        width: Int,
        height: Int,
        margin: Int
    )
}