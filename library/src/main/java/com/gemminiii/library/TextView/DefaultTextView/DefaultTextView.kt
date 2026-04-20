package com.gemminiii.library.TextView.DefaultTextView

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gemminiii.library.Button.DefaultKvantMaterialButton.DefaultMaterialButton
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
import com.gemminiii.library.Containers.DefaultLinearContainer.DefLinContainer
import com.gemminiii.library.R
import com.gemminiii.library.TextView.DefaultTextView.config.TextViewConfig
import com.gemminiii.library.TextView.DefaultTextView.core.DefTvDrawable

class DefaultTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): DefLinContainer(context, attrs, defStyleAttr) {
    private var tvConfig = TextViewConfig()
    private var buttonConfig = ButtonConfig()
    init{
        initAttributes(attrs)
        applyStyles()
    }

    private fun initAttributes(attrs: AttributeSet?){
        attrs?.let{
                context.obtainStyledAttributes(attrs, commonAttrArray).use { typedArray ->
                    val commonAttr = context.readCommonAttributes(attrs)
                    tvConfig.cornersRadiusView = commonAttr.cornersRadius
                    tvConfig.backgroundColorView = commonAttr.backgroundColor
                    tvConfig.strokeWidthView = commonAttr.strokeWidth
                    tvConfig.strokeColorView = commonAttr.strokeColor + tvConfig.strokeWidthView
                    tvConfig.paddingView = commonAttr.padding
                }
                context.obtainStyledAttributes(it, R.styleable.DefaultTextView).apply {
                    try {
                        tvConfig.cornersRadiusTextElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusTextElem,
                            5
                        ).toFloat()
                        tvConfig.backgroundColorTextElem = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorTextElem,
                            R.color.black
                        )
                        tvConfig.strokeWidthTextElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthTextElem,
                            0
                        )
                        tvConfig.strokeColorTextElem = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorTextElem,
                            R.color.black
                        )
                        tvConfig.paddingTextElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorTextElem,
                            0
                        ) + tvConfig.strokeWidthView
                        tvConfig.textElem = getString(R.styleable.DefaultTextView_sTextElem)
                        tvConfig.textSizeElem =
                            getDimensionPixelSize(R.styleable.DefaultTextView_sTextSizeElem, 0)
                        tvConfig.textColorElem =
                            getResourceId(R.styleable.DefaultTextView_sTextColorElem, R.color.black)
                        //textTypefaceElem = getString(R.styleable.DefEditTextViewParam_sTypefaceElem)

                        tvConfig.commonColorFlag =
                            getBoolean(R.styleable.DefaultTextView_sCommonColorFlag, true)
                        tvConfig.commonCornerRadiusFlag =
                            getBoolean(R.styleable.DefaultTextView_sCommonCornerRadiusFlag, true)

                        tvConfig.bottomType =
                            when (getInt(R.styleable.DefaultTextView_sBottomStatusVisibility, 0)) {
                                1 -> DefTvDrawable.BottomElementMode.TEXT
                                2 -> DefTvDrawable.BottomElementMode.PROGRESS
                                else -> DefTvDrawable.BottomElementMode.NONE
                            }

                        tvConfig.buttonVisibilityType =
                            when (getInt(R.styleable.DefaultTextView_sButtonsVisibility, 0)) {
                                1 -> DefTvDrawable.ButtonVisibilityMode.LEFT
                                2 -> DefTvDrawable.ButtonVisibilityMode.RIGHT
                                3 -> DefTvDrawable.ButtonVisibilityMode.ALL
                                else -> DefTvDrawable.ButtonVisibilityMode.NONE
                            }

                        tvConfig.iconResButtonLeft =
                            getResourceId(R.styleable.DefaultTextView_sLeftIcon, 0)
                        tvConfig.iconResButtonRight =
                            getResourceId(R.styleable.DefaultTextView_sRightIcon, 0)

                        //кнопки по краям
                        buttonConfig.cornerRadius = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusAllButton,
                            5
                        ).toFloat()
                        buttonConfig.backgroundColor = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorAllButton,
                            R.color.black
                        )
                        buttonConfig.strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthAllButton,
                            0
                        )
                        buttonConfig.strokeColor = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorAllButton,
                            R.color.black
                        )
                        buttonConfig.padding = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorAllButton,
                            0
                        ) + buttonConfig.strokeWidth

                        tvConfig.cornersRadiusBottomElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sCornerRadiusBottomElem,
                            5
                        ).toFloat()
                        tvConfig.backgroundColorBottomElem = getResourceId(
                            R.styleable.DefaultTextView_sBackgroundColorBottomElem,
                            R.color.black
                        )
                        tvConfig.strokeWidthBottomElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeWidthBottomElem,
                            0
                        )
                        tvConfig.strokeColorBottomElem = getResourceId(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            R.color.black
                        )
                        tvConfig.paddingBottomElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sStrokeColorBottomElem,
                            0
                        ) + tvConfig.strokeWidthView
                        tvConfig.textBottomElem =
                            getString(R.styleable.DefaultTextView_sTextBottomElem)
                        tvConfig.textSizeBottomElem = getDimensionPixelSize(
                            R.styleable.DefaultTextView_sTextSizeBottomElem,
                            0
                        )
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
        }
    }
    private fun applyStyles(){
        try{
            val commonContainer = DefLinContainer(context).apply{
                orientation = LinearLayout.VERTICAL
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            val horizontalContainer = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                weightSum = 1f
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            val leftButton = DefaultMaterialButton(context).apply {
                applyStyles(buttonConfig)
                buttonConfig.iconRes = tvConfig.iconResButtonLeft
                weightSum = 1f
                layoutParams = LinearLayout.LayoutParams(
                    40,
                    40
                )
            }
            val rightButton = DefaultMaterialButton(context).apply {
                applyStyles(buttonConfig)
                buttonConfig.iconRes = tvConfig.iconResButtonRight
                weightSum = 1f
                layoutParams = LinearLayout.LayoutParams(
                    40,
                    40
                )
            }
            horizontalContainer.addView(leftButton)
            horizontalContainer.addView(rightButton)
            commonContainer.addView(horizontalContainer)
        }catch(e: Exception){

        }
    }
}