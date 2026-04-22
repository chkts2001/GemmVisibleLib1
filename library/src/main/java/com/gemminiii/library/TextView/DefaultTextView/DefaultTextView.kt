package com.gemminiii.library.TextView.DefaultTextView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonDrawableConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
import com.gemminiii.library.Containers.DefaultLinearContainer.DefLinContainer
import com.gemminiii.library.R
import com.gemminiii.library.TextView.DefaultTextView.config.CommonConfig
import com.gemminiii.library.TextView.DefaultTextView.config.ProgressBarConfig
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig
import com.gemminiii.library.TextView.DefaultTextView.config.DrawableConfig
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums.BottomElementMode
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums.ButtonVisibilityMode
import com.gemminiii.library.TextView.DefaultTextView.core.ElemTextView
import com.gemminiii.library.TextView.DefaultTextView.implementation.ElemDefMaterialButtonImpl
import com.gemminiii.library.TextView.DefaultTextView.implementation.ElemTextViewImpl
import com.gemminiii.library.TextView.DefaultTextView.implementation.sDrawableImpl

class DefaultTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): DefLinContainer(context, attrs, defStyleAttr) {
    private var commonConfig = CommonConfig()
    private var commonDrawableConfig = DrawableConfig()
    private var buttonDrawableConfig = ButtonDrawableConfig()
    private var buttonConfig = ButtonConfig()
    private var textDrawableConfig = DrawableConfig()
    private var textConfig = TextViewConfig()
    private var bottomTextDrawableConfig = DrawableConfig()
    private var bottomTextConfig = TextViewConfig()
    private var bottomProgressDrawableConfig = DrawableConfig()
    private var bottomProgressConfig = ProgressBarConfig()


    val buttonsFunc = ElemDefMaterialButtonImpl()
    val drawableFunc = sDrawableImpl()
    val textViewFunc = ElemTextViewImpl()

    var commonColor = 0
    init{
        initAttributes(attrs)
        applyStyles()
    }

    private fun initAttributes(attrs: AttributeSet?){
        attrs?.let{

                context.obtainStyledAttributes(attrs, commonAttrArray).use { typedArray ->
                    Log.d("FactoryDebug","3 common")
                    val commonAttr = context.readCommonAttributes(attrs)
                    commonDrawableConfig.cornersRadius = commonAttr.cornersRadius
                    commonDrawableConfig.backgroundColor = commonAttr.backgroundColor
                    commonDrawableConfig.strokeWidth = commonAttr.strokeWidth
                    commonDrawableConfig.strokeColor = commonAttr.strokeColor
                    commonDrawableConfig.padding = commonAttr.padding + commonDrawableConfig.strokeWidth
                    commonDrawableConfig.margin = commonAttr.margin
                    commonConfig.commonColor = commonAttr.commonColor
                }
                context.obtainStyledAttributes(it, R.styleable.DefaultTextView).apply {
                    try {
                        commonConfig.commonColorFlag =
                            getBoolean(R.styleable.DefaultTextView_sCommonColorFlag, true)
                        commonConfig.commonCornerRadiusFlag =
                            getBoolean(R.styleable.DefaultTextView_sCommonCornerRadiusFlag, true)

                        commonConfig.bottomType =
                            when (getInt(R.styleable.DefaultTextView_sBottomStatusVisibility, 0)) {
                                1 -> BottomElementMode.TEXT
                                2 -> BottomElementMode.PROGRESS
                                else -> BottomElementMode.NONE
                            }

                        commonConfig.buttonVisibilityType =
                            when (getInt(R.styleable.DefaultTextView_sButtonsVisibility, 0)) {
                                1 -> ButtonVisibilityMode.LEFT
                                2 -> ButtonVisibilityMode.RIGHT
                                3 -> ButtonVisibilityMode.ALL
                                else -> ButtonVisibilityMode.NONE
                            }

                        commonConfig.iconResButtonLeft =
                            getResourceId(R.styleable.DefaultTextView_sLeftIcon, 0)
                        commonConfig.iconResButtonRight =
                            getResourceId(R.styleable.DefaultTextView_sRightIcon, 0)


                        textDrawableConfig.cornersRadius = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusTextElem,
                            5
                        ).toFloat()
                        textDrawableConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorTextElem,
                            R.color.black
                        )
                        textDrawableConfig.strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthTextElem,
                            0
                        )
                        Log.d("FactoryDebug", "commonColor: ${commonConfig.commonColor}")
                        textDrawableConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorTextElem,
                            R.color.black
                        )
                        textDrawableConfig.padding = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorTextElem,
                            0
                        ) + textDrawableConfig.strokeWidth)
                        textDrawableConfig.margin = commonDrawableConfig.margin
                        textConfig.textTv = getString(R.styleable.DefaultTextView_sTextElem)
                        textConfig.textSizeTv = getDimensionPixelSize(R.styleable.DefaultTextView_sTextSizeElem, 14)
                        textConfig.textColorTv =
                            getResourceId(R.styleable.DefaultTextView_sTextColorElem, R.color.black)
                        //textTypefaceElem = getString(R.styleable.DefEditTextViewParam_sTypefaceElem)

                        buttonConfig.width = 40
                        buttonConfig.height = 40
                        //кнопки по краям
                        buttonDrawableConfig.cornerRadius = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusAllButton,
                            5
                        ).toFloat()
                        Log.d("FactoryDebug", "commonColor inside: ${ commonConfig.commonColor} and ${android.R.color.holo_blue_dark}")
                        //buttonConfig.backgroundColor = this@DefaultTextView.commonColor
                        buttonDrawableConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorAllButton,
                            commonConfig.commonColor
                        )
                        buttonDrawableConfig.strokeWidth = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthAllButton,
                            0
                        ))
                        buttonDrawableConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorAllButton,
                            R.color.black
                        )
                        buttonDrawableConfig.padding = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sPaddingAllButton,
                            0
                        ) + buttonDrawableConfig.strokeWidth)
                        buttonDrawableConfig.margin = commonDrawableConfig.margin

                        bottomTextDrawableConfig.cornersRadius = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusBottomElem,
                            5
                        ).toFloat()
                        bottomTextDrawableConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorBottomElem,
                            R.color.black
                        )
                        bottomTextDrawableConfig.strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthBottomElem,
                            0
                        )
                        bottomTextDrawableConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            R.color.black
                        )
                        bottomTextDrawableConfig.padding = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            0
                        ) + bottomTextDrawableConfig.strokeWidth)
                        bottomTextDrawableConfig.margin = commonDrawableConfig.margin

                        bottomTextConfig.textTv =
                            getString(R.styleable.DefaultTextView_sTextBottomElem)
                        bottomTextConfig.textSizeTv = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sTextSizeBottomElem,
                            0
                        ))
                        bottomTextConfig.textColorTv = getResourceId(
                            R.styleable.DefaultTextView_sTextColorBottomElem,
                            R.color.black
                        )
                        buttonConfig.iconSize =
                            getDimensionPixelSize(R.styleable.DefaultTextView_sIconSize, 24)
                        buttonConfig.iconTint =
                            getResourceId(R.styleable.DefaultTextView_sIconTint, R.color.white)
                        buttonConfig.iconGravity =
                            when (getInt(R.styleable.DefaultTextView_sIconGravity, 2)) {
                                0 -> ButtonIcon.IconPosition.START
                                1 -> ButtonIcon.IconPosition.END
                                2 -> ButtonIcon.IconPosition.CENTER
                                else -> ButtonIcon.IconPosition.CENTER
                            }
                        //textTypefaceBottomElem
                    } catch (e: Exception) {

                    }
                }
            Log.d("FactoryDebug", "commonColor outside: ${commonConfig.commonColor}")
            if(commonConfig.commonColor != 0){
                commonDrawableConfig.strokeColor = commonConfig.commonColor
                buttonDrawableConfig.backgroundColor = commonConfig.commonColor
                textDrawableConfig.strokeColor = commonConfig.commonColor
                textConfig.textColorTv = commonConfig.commonColor
            }
        }
    }
    private fun applyStyles(){
        try{
            val horizontalContainer = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            }
//            val leftButton = DefaultMaterialButton(context).apply {
//                buttonConfig.iconRes = tvConfig.iconResButtonLeft
//                buttonConfig.iconGravity = ButtonIcon.IconPosition.CENTER
//                applyStyles(buttonConfig)
//            }
//            val rightButton = DefaultMaterialButton(context).apply {
//                buttonConfig.iconRes = tvConfig.iconResButtonRight
//                buttonConfig.iconGravity = ButtonIcon.IconPosition.CENTER
//                applyStyles(buttonConfig)
//            }
            val buttons = buttonsFunc.elemDefMaterialButtonCreate(context, commonConfig.iconResButtonLeft, commonConfig.iconResButtonRight,  commonConfig.buttonVisibilityType, buttonDrawableConfig, buttonConfig)
            //val textElem = textViewFunc.textViewCreate(context, 0, LinearLayout.LayoutParams.MATCH_PARENT, drawableFunc.createDrawable(textDrawableConfig.cornersRadius, textDrawableConfig.backgroundColor, textDrawableConfig.strokeWidth, textDrawableConfig.strokeColor), textConfig)
            val textElem = textViewFunc.textViewCreate(context, textDrawableConfig, textConfig)

            horizontalContainer.addView(buttons.first)
            horizontalContainer.addView(textElem)
            horizontalContainer.addView(buttons.second)
            horizontalContainer.post {
                buttonsFunc.elemDefMaterialButtonUpdateSize(horizontalContainer.height, LinearLayout.LayoutParams.MATCH_PARENT, buttonDrawableConfig.margin)
                textViewFunc.textViewUpdateSize(0, LinearLayout.LayoutParams.MATCH_PARENT, textDrawableConfig.margin)
            }
            this.addView(horizontalContainer)

//            this.post {
//                this.layoutParams = LinearLayout.LayoutParams(this.width, this.height + commonDrawableConfig.padding * 2)
//            }

        }catch(e: Exception){

        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}