package com.gemminiii.library.Universal.UniversalFieldValue

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonDrawableConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
import com.gemminiii.library.Containers.DefaultLinearContainer.DefLinContainer
import com.gemminiii.library.R
import com.gemminiii.library.Universal.UniversalFieldValue.config.CommonConfig
import com.gemminiii.library.Universal.UniversalFieldValue.config.ProgressBarConfig
import com.gemminiii.library.Universal.UniversalFieldValue.config.TextElemConfig
import com.gemminiii.library.Universal.UniversalFieldValue.config.DrawableConfig
import com.gemminiii.library.Universal.UniversalFieldValue.implementation.ElemDefMaterialButtonImpl
import com.gemminiii.library.Universal.UniversalFieldValue.implementation.ElemHorizontalProgressBarImpl
import com.gemminiii.library.Universal.UniversalFieldValue.implementation.ElemTextViewImpl
import com.gemminiii.library.Universal.UniversalFieldValue.implementation.sDrawableImpl
import com.gemminiii.library.Universal.UniversalFieldValue.core.CommonEnums

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
    private var textConfig = TextElemConfig()
    private var bottomElemDrawableConfig = DrawableConfig()
    private var bottomTextConfig = TextElemConfig()
    private var bottomProgressConfig = ProgressBarConfig()
    private var headerDrawableConfig = DrawableConfig()
    private var headerConfig = TextElemConfig()


    val buttonsFunc = ElemDefMaterialButtonImpl()
    val drawableFunc = sDrawableImpl()
    val textViewFunc = ElemTextViewImpl()
    val bottomProgressFunc = ElemHorizontalProgressBarImpl()
    val bottomTextFunc = ElemTextViewImpl()
    val headerTextFunc = ElemTextViewImpl()

    var commonColor = 0
    init{
        orientation = VERTICAL
        setWillNotDraw(false)
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
                            when (getInt(R.styleable.DefaultTextView_sBottomElemMode, 0)) {
                                1 -> CommonEnums.BottomElementMode.TEXT
                                2 -> CommonEnums.BottomElementMode.PROGRESS
                                else -> CommonEnums.BottomElementMode.NONE
                            }

                        commonConfig.buttonVisibilityType =
                            when (getInt(R.styleable.DefaultTextView_sButtonsVisibility, 0)) {
                                1 -> CommonEnums.ButtonVisibilityMode.LEFT
                                2 -> CommonEnums.ButtonVisibilityMode.RIGHT
                                3 -> CommonEnums.ButtonVisibilityMode.ALL
                                else -> CommonEnums.ButtonVisibilityMode.NONE
                            }
                        commonConfig.headerPositionMode =
                            when(getInt(R.styleable.DefaultTextView_sHeaderPositionMode, 0)){
                                1 -> CommonEnums.HeaderPositionMode.TOP
                                2 -> CommonEnums.HeaderPositionMode.LEFT
                                3 -> CommonEnums.HeaderPositionMode.RIGHT
                                else -> CommonEnums.HeaderPositionMode.NONE
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
                            R.styleable.DefaultTextView_sPaddingTextElem,
                            0
                        ) + textDrawableConfig.strokeWidth)
                        textDrawableConfig.margin = commonDrawableConfig.margin
                        textConfig.textTv = getString(R.styleable.DefaultTextView_sTextElem)
                        textConfig.textSizeTv = (getDimension(R.styleable.DefaultTextView_sTextSizeElem, dpToPx(14).toFloat()) / resources.displayMetrics.density).toInt()
                        textConfig.textColorTv =
                            getResourceId(R.styleable.DefaultTextView_sTextColorElem, R.color.black)
                        textConfig.textHint = getString(R.styleable.DefaultTextView_sTextHeader)
                        //textTypefaceElem = getString(R.styleable.DefEditTextViewParam_sTypefaceElem)

                        //buttons element
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

                        // bottom element
                        bottomElemDrawableConfig.cornersRadius = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusBottomElem,
                            5
                        ).toFloat()
                        bottomElemDrawableConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorBottomElem,
                            android.R.color.transparent
                        )
                        bottomElemDrawableConfig.strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthBottomElem,
                            0
                        )
                        bottomElemDrawableConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            R.color.black
                        )
                        bottomElemDrawableConfig.padding = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            0
                        ) + bottomElemDrawableConfig.strokeWidth)
                        bottomElemDrawableConfig.margin = commonDrawableConfig.margin

                        bottomTextConfig.textTv = getString(R.styleable.DefaultTextView_sTextBottomElem)
                        bottomTextConfig.textSizeTv = (getDimension(R.styleable.DefaultTextView_sTextSizeBottomElem, dpToPx(14).toFloat()) / resources.displayMetrics.density).toInt()
                        bottomTextConfig.textColorTv = getResourceId(
                            R.styleable.DefaultTextView_sTextColorBottomElem,
                            android.R.color.holo_blue_dark
                        )
                        bottomProgressConfig.bottomProgressBackgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBottomProgressBackgroundColor,
                            android.R.color.transparent)
                        bottomProgressConfig.bottomProgressTrackColor = getResourceId(
                            R.styleable.DefaultTextView_sBottomProgressTrackColor, R.color.black
                        )

                        //headerElement
                        headerDrawableConfig.cornersRadius = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusHeader,
                            5
                        ).toFloat()
                        headerDrawableConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorHeader,
                            android.R.color.transparent
                        )
                        headerDrawableConfig.strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthHeader,
                            0
                        )
                        headerDrawableConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorHeader,
                            R.color.black
                        )
                        headerDrawableConfig.padding = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorHeader,
                            0
                        ) + bottomElemDrawableConfig.strokeWidth)
                        headerDrawableConfig.margin = commonDrawableConfig.margin
                        headerConfig.textTv = getString(R.styleable.DefaultTextView_sTextHeader)
                        headerConfig.textSizeTv = (getDimension(R.styleable.DefaultTextView_sTextSizeHeader, dpToPx(14).toFloat()) / resources.displayMetrics.density).toInt()
                        headerConfig.textColorTv =
                            getResourceId(R.styleable.DefaultTextView_sTextColorHeader, android.R.color.holo_blue_dark)
                        //textTypefaceBottomElem
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e("Builder", "attr error: ${e.message}", e)
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
                orientation = HORIZONTAL
                layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            }

            val commonTextFriendVerticalContainer = LinearLayout(context).apply {
                orientation = VERTICAL
                layoutParams = LayoutParams(
                    0,
                    LayoutParams.WRAP_CONTENT
                ).apply {
                    weight = 1f
                }
            }
            val headerElem = headerTextFunc.textViewCreate(context, headerDrawableConfig, headerConfig, Gravity.START or Gravity.CENTER)
            val buttons = buttonsFunc.elemDefMaterialButtonCreate(context, commonConfig.iconResButtonLeft, commonConfig.iconResButtonRight,  commonConfig.buttonVisibilityType, buttonDrawableConfig, buttonConfig)
            //val textElem = textViewFunc.textViewCreate(context, 0, LinearLayout.LayoutParams.MATCH_PARENT, drawableFunc.createDrawable(textDrawableConfig.cornersRadius, textDrawableConfig.backgroundColor, textDrawableConfig.strokeWidth, textDrawableConfig.strokeColor), textConfig)
            val textElem = textViewFunc.textViewCreate(context, textDrawableConfig, textConfig,
                Gravity.START or Gravity.CENTER)
            val bottomElemText = bottomTextFunc.textViewCreate(context, bottomElemDrawableConfig, bottomTextConfig, Gravity.START or Gravity.CENTER)
            val bottomProgress= bottomProgressFunc.horizontalProgressBarCreate(context, bottomElemDrawableConfig, bottomProgressConfig)

            //common text friends container
            commonTextFriendVerticalContainer.addView(textElem)
            if(commonConfig.bottomType == CommonEnums.BottomElementMode.TEXT) commonTextFriendVerticalContainer.addView(bottomElemText)
            else if(commonConfig.bottomType == CommonEnums.BottomElementMode.PROGRESS) commonTextFriendVerticalContainer.addView(bottomProgress)

            commonTextFriendVerticalContainer.post {
                textViewFunc.textViewUpdateSize(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, textDrawableConfig.margin)
                if(commonConfig.bottomType == CommonEnums.BottomElementMode.TEXT) bottomTextFunc.textViewUpdateSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, bottomElemDrawableConfig.margin)
                else if(commonConfig.bottomType == CommonEnums.BottomElementMode.PROGRESS)bottomProgressFunc.horizontalProgressBarUpdateSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, bottomElemDrawableConfig.margin)
            }

            // horizontal container
            if(commonConfig.headerPositionMode == CommonEnums.HeaderPositionMode.LEFT) horizontalContainer.addView(headerElem)
            horizontalContainer.addView(buttons.first)
            horizontalContainer.addView(commonTextFriendVerticalContainer)
            horizontalContainer.addView(buttons.second)
            if(commonConfig.headerPositionMode == CommonEnums.HeaderPositionMode.RIGHT) horizontalContainer.addView(headerElem)
            horizontalContainer.post {
                buttonsFunc.elemDefMaterialButtonUpdateSize(textElem.height, textElem.height, buttonDrawableConfig.margin)
                if(commonConfig.headerPositionMode == CommonEnums.HeaderPositionMode.RIGHT || commonConfig.headerPositionMode == CommonEnums.HeaderPositionMode.LEFT) headerTextFunc.textViewUpdateSize(LayoutParams.WRAP_CONTENT, textElem.height, headerDrawableConfig.margin)
            }

            //parent container
            if(commonConfig.headerPositionMode == CommonEnums.HeaderPositionMode.TOP) this.addView(headerElem)
            this.addView(horizontalContainer)
            this.post {
                headerTextFunc.textViewUpdateSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, headerDrawableConfig.margin, 0f)
            }

        }catch(e: Exception){
            e.printStackTrace()
            Log.e("Builder", "applyStyles error: ${e.message}", e)
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}