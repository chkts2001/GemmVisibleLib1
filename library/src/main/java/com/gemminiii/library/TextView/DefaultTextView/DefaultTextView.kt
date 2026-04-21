package com.gemminiii.library.TextView.DefaultTextView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
import com.gemminiii.library.Containers.DefaultLinearContainer.DefLinContainer
import com.gemminiii.library.R
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums.BottomElementMode
import com.gemminiii.library.TextView.DefaultTextView.core.CommonEnums.ButtonVisibilityMode
import com.gemminiii.library.TextView.DefaultTextView.core.ElemDefMaterialButton
import com.gemminiii.library.TextView.DefaultTextView.implementation.ElemDefMaterialButtonImpl

class DefaultTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): DefLinContainer(context, attrs, defStyleAttr) {
    private var tvConfig = TextViewConfig()
    private var buttonConfig = ButtonConfig()
    val buttonsFuncs = ElemDefMaterialButtonImpl()
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
                    tvConfig.cornersRadiusView = commonAttr.cornersRadius
                    tvConfig.backgroundColorView = commonAttr.backgroundColor
                    tvConfig.strokeWidthView = commonAttr.strokeWidth
                    tvConfig.strokeColorView = commonAttr.strokeColor
                    tvConfig.paddingView = commonAttr.padding + tvConfig.strokeWidthView
                    tvConfig.marginView = commonAttr.margin
                    tvConfig.commonColor = commonAttr.commonColor
                }
                context.obtainStyledAttributes(it, R.styleable.DefaultTextView).apply {
                    try {
                        tvConfig.cornersRadiusTextElem = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusTextElem,
                            5
                        )).toFloat()
                        tvConfig.backgroundColorTextElem = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorTextElem,
                            R.color.black
                        )
                        tvConfig.strokeWidthTextElem = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthTextElem,
                            0
                        ))
                        Log.d("FactoryDebug", "commonColor: ${tvConfig.commonColor}")
                        tvConfig.strokeColorTextElem = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorTextElem,
                            R.color.black
                        )
                        tvConfig.paddingTextElem = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorTextElem,
                            0
                        ) + tvConfig.strokeWidthView)
                        tvConfig.textElem = getString(R.styleable.DefaultTextView_sTextElem)
                        tvConfig.textSizeElem =
                            dpToPx(getDimensionPixelSize(R.styleable.DefaultTextView_sTextSizeElem, 0))
                        tvConfig.textColorElem =
                            getResourceId(R.styleable.DefaultTextView_sTextColorElem, R.color.black)
                        //textTypefaceElem = getString(R.styleable.DefEditTextViewParam_sTypefaceElem)

                        tvConfig.commonColorFlag =
                            getBoolean(R.styleable.DefaultTextView_sCommonColorFlag, true)
                        tvConfig.commonCornerRadiusFlag =
                            getBoolean(R.styleable.DefaultTextView_sCommonCornerRadiusFlag, true)

                        tvConfig.bottomType =
                            when (getInt(R.styleable.DefaultTextView_sBottomStatusVisibility, 0)) {
                                1 -> BottomElementMode.TEXT
                                2 -> BottomElementMode.PROGRESS
                                else -> BottomElementMode.NONE
                            }

                        tvConfig.buttonVisibilityType =
                            when (getInt(R.styleable.DefaultTextView_sButtonsVisibility, 0)) {
                                1 -> ButtonVisibilityMode.LEFT
                                2 -> ButtonVisibilityMode.RIGHT
                                3 -> ButtonVisibilityMode.ALL
                                else -> ButtonVisibilityMode.NONE
                            }

                        tvConfig.iconResButtonLeft =
                            getResourceId(R.styleable.DefaultTextView_sLeftIcon, 0)
                        tvConfig.iconResButtonRight =
                            getResourceId(R.styleable.DefaultTextView_sRightIcon, 0)

                        buttonConfig.width = 40
                        buttonConfig.height = 40
                        //кнопки по краям
                        buttonConfig.cornerRadius = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusAllButton,
                            5
                        )).toFloat()
                        Log.d("FactoryDebug", "commonColor inside: ${ tvConfig.commonColor} and ${android.R.color.holo_blue_dark}")
                        //buttonConfig.backgroundColor = this@DefaultTextView.commonColor
                        buttonConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorAllButton,
                            tvConfig.commonColor
                        )
                        buttonConfig.strokeWidth = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthAllButton,
                            0
                        ))
                        buttonConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorAllButton,
                            R.color.black
                        )
                        buttonConfig.padding = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sPaddingAllButton,
                            0
                        ) + buttonConfig.strokeWidth)
                        buttonConfig.margin = getDimensionPixelSize(R.styleable.DefaultTextView_sMarginAllButton, 0)

                        tvConfig.cornersRadiusBottomElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusBottomElem,
                            5
                        ).toFloat()
                        tvConfig.backgroundColorBottomElem = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorBottomElem,
                            R.color.black
                        )
                        tvConfig.strokeWidthBottomElem = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthBottomElem,
                            0
                        ))
                        tvConfig.strokeColorBottomElem = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            R.color.black
                        )
                        tvConfig.paddingBottomElem = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            0
                        ) + tvConfig.strokeWidthView)
                        tvConfig.textBottomElem =
                            getString(R.styleable.DefaultTextView_sTextBottomElem)
                        tvConfig.textSizeBottomElem = dpToPx(getDimensionPixelSize(
                            R.styleable.DefaultTextView_sTextSizeBottomElem,
                            0
                        ))
                        tvConfig.textColorBottomElem = getResourceId(
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
            Log.d("FactoryDebug", "commonColor outside: ${tvConfig.commonColor}")
            if(tvConfig.commonColor != 0){
                tvConfig.strokeColorView = tvConfig.commonColor
                buttonConfig.backgroundColor = tvConfig.commonColor
            }
        }
    }
    private fun applyStyles(){
        try{
//            val commonContainer = DefLinContainer(context).apply{
//                orientation = LinearLayout.VERTICAL
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//            }
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
            val buttons =
            horizontalContainer.addView(leftButton)
            horizontalContainer.addView(rightButton)
            horizontalContainer.post {
                leftButton.layoutParams = LinearLayout.LayoutParams(horizontalContainer.height,
                    LinearLayout.LayoutParams.MATCH_PARENT).apply { setMargins(buttonConfig.margin / 2, buttonConfig.margin, buttonConfig.margin, buttonConfig.margin) }
                rightButton.layoutParams = LinearLayout.LayoutParams(horizontalContainer.height,
                    LinearLayout.LayoutParams.MATCH_PARENT).apply { setMargins(buttonConfig.margin, buttonConfig.margin, buttonConfig.margin / 2, buttonConfig.margin) }
            }

            this.addView(horizontalContainer)
        }catch(e: Exception){

        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}