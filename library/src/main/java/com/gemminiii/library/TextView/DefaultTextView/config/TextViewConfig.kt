package com.gemminiii.library.TextView.DefaultTextView.config

import android.graphics.Typeface
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.TextView.DefaultTextView.core.DefTvDrawable

class TextViewConfig(
    //common параметры
    var commonColorFlag: Boolean = true,
    var commonCornerRadiusFlag: Boolean = true,
    var bottomType: DefTvDrawable.BottomElementMode = DefTvDrawable.BottomElementMode.NONE,
    var buttonVisibilityType: DefTvDrawable.ButtonVisibilityMode = DefTvDrawable.ButtonVisibilityMode.NONE,

    //фундаментальный LinearLayout
    var cornersRadiusView: Float = 5f,
    var backgroundColorView: Int = android.R.color.transparent,
    var strokeWidthView: Int = 5,
    var strokeColorView: Int = android.R.color.black,
    var paddingView:Int = 0,

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
    var cornersRadiusAllButton: Float = 5f,
    var backgroundColorAllButton: Int = android.R.color.transparent,
    var strokeWidthAllButton: Int = 5,
    var strokeColorAllButton: Int = android.R.color.black,
    var paddingAllButton:Int = 0,
    var iconResButtonLeft: Int? = null,
    var iconResButtonRight: Int? = null,
    var iconSizeButtonAll: Float = 24f,
    var iconTintButtonAll: Int = android.R.color.white,
    var iconGravityButtonAll: ButtonIcon.IconPosition = ButtonIcon.IconPosition.CENTER,

    // Нижний элемент текст/прогресс
    var cornersRadiusBottomElem: Float = 5f,
    var backgroundColorBottomElem: Int = android.R.color.transparent,
    var strokeWidthBottomElem: Int = 5,
    var strokeColorBottomElem: Int = android.R.color.black,
    var paddingBottomElem:Int = 0,
    var textBottomElem: String? = null,
    var textSizeBottomElem: Int = 14,
    var textColorBottomElem: Int = android.R.color.black,
    var textTypefaceBottomElem: Typeface? = null,
)