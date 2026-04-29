package com.gemminiii.library.Universal.UniversalFieldValue.config

import com.gemminiii.library.Universal.UniversalFieldValue.core.CommonEnums

class CommonConfig(
    //common параметры
    var commonColorFlag: Boolean = true,
    var commonCornerRadiusFlag: Boolean = true,
    var commonColor: Int = 0,
    var bottomType: CommonEnums.BottomElementMode = CommonEnums.BottomElementMode.NONE,
    var buttonVisibilityType: CommonEnums.ButtonVisibilityMode = CommonEnums.ButtonVisibilityMode.NONE,
    var headerPositionMode: CommonEnums.HeaderPositionMode = CommonEnums.HeaderPositionMode.NONE,

    // Левая/Правая кнопки
    var iconResButtonLeft: Int? = null,
    var iconResButtonRight: Int? = null

    // Нижний элемент drawable

    //Нижний элемент textView

    //Нижний элемент progressBar
)