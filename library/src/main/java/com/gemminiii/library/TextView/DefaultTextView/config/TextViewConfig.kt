package com.gemminiii.library.TextView.DefaultTextView.config

import android.graphics.Typeface
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums

class TextViewConfig(
    //common параметры
    var commonColorFlag: Boolean = true,
    var commonCornerRadiusFlag: Boolean = true,
    var commonColor: Int = 0,
    var bottomType: CommonEnums.BottomElementMode = CommonEnums.BottomElementMode.NONE,
    var buttonVisibilityType: CommonEnums.ButtonVisibilityMode = CommonEnums.ButtonVisibilityMode.NONE,

    //фундаментальный LinearLayout
    var cornersRadiusView: Float = 5f,
    var backgroundColorView: Int = android.R.color.transparent,
    var strokeWidthView: Int = 5,
    var strokeColorView: Int = android.R.color.black,
    var paddingView:Int = 0,
    var marginView: Int = 0,

    //EditText, куда будем вводить ифнормацию
    var cornersRadiusTextElem: Float = 5f,
    var backgroundColorTextElem: Int = android.R.color.transparent,
    var strokeWidthTextElem: Int = 5,
    var strokeColorTextElem: Int = android.R.color.black,
    var paddingTextElem:Int = 0,
    var textElem: String? = null,
    var textSizeElem: Int = 14,
    var textColorElem: Int = android.R.color.black,
    var textTypefaceElem: Typeface? = null,

    // Левая/Правая кнопки
    var iconResButtonLeft: Int? = null,
    var iconResButtonRight: Int? = null,

    // Нижний элемент drawable
    var cornersRadiusBottomElem: Float = 5f,
    var backgroundColorBottomElem: Int = android.R.color.transparent,
    var strokeWidthBottomElem: Int = 5,
    var strokeColorBottomElem: Int = android.R.color.black,
    var paddingBottomElem:Int = 0,

    //Нижний элемент textView
    var textBottomElem: String? = null,
    var textSizeBottomElem: Int = 14,
    var textColorBottomElem: Int = android.R.color.black,
    var textTypefaceBottomElem: Typeface? = null,

    //Нижний элемент progressBar
    var bottomProgressTintColor: Int,
    var bottomProgressBackground: Int
)