package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonDrawable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonState
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonText
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.ButtonDrawableImpl
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.ButtonStateImpl
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.IconButtonImpl
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.TextStyleImpl
import com.gemminiii.library.R
import com.google.android.material.button.MaterialButton

class DefaultMaterialButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): MaterialButton(context, attrs, defStyleAttr) {
    private val buttonDrawable: ButtonDrawable = ButtonDrawableImpl()
    private val buttonIcon: ButtonIcon = IconButtonImpl()
    private val buttonState: ButtonState = ButtonStateImpl()
    private val buttonText: ButtonText = TextStyleImpl()
    private var cornerRadius: Float = 0f
    private var backgroundColor: Int = Color.TRANSPARENT
    private var gradientStartColor: Int = Color.TRANSPARENT
    private var gradientEndColor: Int = Color.TRANSPARENT
    private var strokeWidth: Int = 0
    private var strokeColor: Int = Color.TRANSPARENT

    private var iconDrawable: android.graphics.drawable.Drawable? = null
    private var iconPosition: ButtonIcon.IconPosition = ButtonIcon.IconPosition.START
    private var iconPadding: Int = 0

    private var customTextColor: ColorStateList? = null
    private var customTextSize: Float = 0f
    private var customTypeface: Typeface? = null

    private var scaleAnimator: ObjectAnimator? = null

    init {
        initAttributes(attrs)
        applyStyles()
    }

    private fun initAttributes(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.DefaultMaterialButton).apply {
                try {
                    // Основные параметры
                    cornerRadius = getDimension(R.styleable.DefaultMaterialButton_buttonCornerRadius,
                        resources.getDimension(R.dimen.default_corner_radius))
                    backgroundColor = getColor(R.styleable.DefaultMaterialButton_buttonBackgroundColor,
                        Color.TRANSPARENT)
                    strokeWidth = getDimensionPixelSize(R.styleable.DefaultMaterialButton_buttonStrokeWidth, 0)
                    strokeColor = getColor(R.styleable.DefaultMaterialButton_buttonStrokeColor, Color.TRANSPARENT)

                    // Иконка
                    val iconRes = getResourceId(R.styleable.DefaultMaterialButton_buttonIcon, 0)
                    iconDrawable = if (iconRes != 0) ContextCompat.getDrawable(context, iconRes) else null
                    iconPosition = when (getInt(R.styleable.DefaultMaterialButton_buttonIconPosition, 2)) {
                        0 -> ButtonIcon.IconPosition.START
                        1 -> ButtonIcon.IconPosition.END
                        2 -> ButtonIcon.IconPosition.CENTER
                        else -> ButtonIcon.IconPosition.CENTER
                    }

                    // Текст
                    customTextColor = getColorStateList(R.styleable.DefaultMaterialButton_buttonTextColor)
                    customTextSize = getDimension(R.styleable.DefaultMaterialButton_buttonTextSize, 0f)

                    val typefaceRes = getString(R.styleable.DefaultMaterialButton_buttonTypeface)
                    customTypeface = typefaceRes?.let { Typeface.create(it, Typeface.NORMAL) }

                } finally {
                    recycle()
                }
            }
        }
    }

    private fun applyStyles() {
        try {
            // Применяем фон
            background = buttonDrawable.createBackground(
                cornerRadius,
                backgroundColor,
                strokeWidth,
                strokeColor
            )

            // Применяем текст
            val textColor = customTextColor ?: buttonState.getStateColors()
            if (textColor != null) {
                buttonText.applyTextStyle(
                    this,
                    text?.toString(),
                    customTextColor ?: buttonState.getStateColors(),
                    customTextSize.takeIf { it > 0 } ?: textSize,
                    customTypeface
                )
            }

            // Применяем иконку
            iconDrawable?.let {
                buttonIcon.applyIcon(this, it, iconPosition)
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

//    private fun setupAnimations() {
//        scaleAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1f, buttonState.getPressedScale()).apply {
//            duration = 100
//            interpolator = OvershootInterpolator()
//        }
//    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                scaleAnimator?.cancel()
                scaleAnimator = ObjectAnimator.ofFloat(this, "scaleX", buttonState.getPressedScale()).apply {
                    duration = 100
                    start()
                }
                ObjectAnimator.ofFloat(this, "scaleY", buttonState.getPressedScale()).apply {
                    duration = 100
                    start()
                }
                alpha = buttonState.getStateAlpha()[0]
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                scaleAnimator?.cancel()
                scaleAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1f).apply {
                    duration = 150
                    start()
                }
                ObjectAnimator.ofFloat(this, "scaleY", 1f).apply {
                    duration = 150
                    start()
                }
                alpha = 1f
            }
        }
        return super.onTouchEvent(event)
    }

    // Публичные методы для программной настройки

    fun setGradientColors(startColor: Int, endColor: Int) {
        gradientStartColor = startColor
        gradientEndColor = endColor
        buttonDrawable.updateBackground(
            background,
            cornerRadius,
            backgroundColor,
            strokeWidth,
            strokeColor
        )
    }

    fun setCornerRadius(radius: Float) {
        cornerRadius = radius
        buttonDrawable.updateBackground(
            background,
            cornerRadius,
            backgroundColor,
            strokeWidth,
            strokeColor
        )
    }

    fun setIcon(icon: android.graphics.drawable.Drawable?, position: ButtonIcon.IconPosition = this.iconPosition) {
        iconDrawable = icon
        iconDrawable?.let {
            buttonIcon.applyIcon(this, it, position)
        } ?: buttonIcon.removeIcon(this)
    }

    override fun setTextColor(colorStateList: ColorStateList) {
        customTextColor = colorStateList
        buttonText.updateTextColor(this, colorStateList)
    }

    override fun setTextSize(size: Float) {
        customTextSize = size
        buttonText.updateTextSize(this, size)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scaleAnimator?.cancel()
        scaleAnimator = null
    }
}