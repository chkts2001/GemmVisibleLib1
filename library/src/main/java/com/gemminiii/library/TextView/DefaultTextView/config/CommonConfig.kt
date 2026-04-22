package com.gemminiii.library.TextView.DefaultTextView.config

import android.graphics.Typeface
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums

class CommonConfig(
    //common параметры
    var commonColorFlag: Boolean = true,
    var commonCornerRadiusFlag: Boolean = true,
    var commonColor: Int = 0,
    var bottomType: CommonEnums.BottomElementMode = CommonEnums.BottomElementMode.NONE,
    var buttonVisibilityType: CommonEnums.ButtonVisibilityMode = CommonEnums.ButtonVisibilityMode.NONE,

    // Левая/Правая кнопки
    var iconResButtonLeft: Int? = null,
    var iconResButtonRight: Int? = null

    // Нижний элемент drawable

    //Нижний элемент textView

    //Нижний элемент progressBar
)