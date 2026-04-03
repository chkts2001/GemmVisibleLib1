package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder
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
): MaterialButton(context, attrs, defStyleAttr), ButtonConfigurable<DefaultMaterialButton> {
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
    private var buttonPadding: Int = 6

    private var iconDrawable: android.graphics.drawable.Drawable? = null
    private var iconPosition: ButtonIcon.IconPosition = ButtonIcon.IconPosition.START
    private var iconSize: Int = 0
    private var iconTint: ColorStateList? = null

    private var iconPadding: Int = 0

    private var customTextColor: ColorStateList? = null
    private var customTextSize: Float = 12f
    private var textColor = R.color.white
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
                    buttonPadding = getDimensionPixelSize(R.styleable.DefaultMaterialButton_buttonPadding, 6)

                    // Иконка
                    val iconRes = getResourceId(R.styleable.DefaultMaterialButton_buttonIcon, 0)
                    iconDrawable = if (iconRes != 0) ContextCompat.getDrawable(context, iconRes) else null
                    iconSize = getDimensionPixelSize(R.styleable.DefaultMaterialButton_buttonIconSize, 25)
                    iconTint = getColorStateList(R.styleable.DefaultMaterialButton_buttonIconTint)
                    iconPosition = when (getInt(R.styleable.DefaultMaterialButton_buttonIconGravity, 2)) {
                        0 -> ButtonIcon.IconPosition.START
                        1 -> ButtonIcon.IconPosition.END
                        2 -> ButtonIcon.IconPosition.CENTER
                        else -> ButtonIcon.IconPosition.CENTER
                    }

                    // Текст
                    customTextColor = getColorStateList(R.styleable.DefaultMaterialButton_buttonTextColor)
                    customTextSize = getDimensionPixelSize(R.styleable.DefaultMaterialButton_buttonTextSize, 12) / resources.displayMetrics.scaledDensity

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
            backgroundTintList = null
            // Применяем фон
            background = buttonDrawable.createBackground(
                cornerRadius,
                backgroundColor,
                strokeWidth,
                strokeColor,
            )
            val pxPadding = dpToPx(buttonPadding)
            //super.setPadding( pxPadding, 0, pxPadding, 0)
            super.setPadding( buttonPadding, 0, buttonPadding, 0)

            if (customTextColor != null) {
                super.setTextColor(customTextColor!!)
            }

            if (customTextSize > 0) {
                super.setTextSize(customTextSize)
            }

            customTypeface?.let {
                super.setTypeface(it)
            }

            // Применяем иконку
            iconDrawable?.let {
                buttonIcon.applyIcon(this, it, iconSize, iconTint, iconPosition)
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

    override fun sCornerRadius(radius: Float): DefaultMaterialButton = apply {
        this.cornerRadius = radius
        applyStyles()
    }
    override fun sBackgroundColor(color: Int): DefaultMaterialButton = apply {
        this.backgroundColor = color
        applyStyles()
    }
    override fun sStroke(width: Int, color: Int): DefaultMaterialButton = apply {
        this.strokeWidth = width
        this.strokeColor = color
        applyStyles()
    }

    override fun sGradient(startColor: Int, endColor: Int): DefaultMaterialButton = apply {
        this.gradientStartColor = startColor
        this.gradientEndColor = endColor
    }

    override fun sPaddings(padding: Int): DefaultMaterialButton = apply{
        this.buttonPadding = padding
        super.setPadding( buttonPadding, 0, buttonPadding, 0)
    }
    override fun sIcon(iconRes: Int, iconSize: Int, iconTint: Int): DefaultMaterialButton = apply {
        this.icon = ContextCompat.getDrawable(context, iconRes)
        this.iconSize = (iconSize * resources.displayMetrics.density).toInt()
        this.iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, iconTint))
        applyStyles()

    }
    override fun sIconGravity(position: ButtonIcon.IconPosition): DefaultMaterialButton = apply {
        this.iconPosition = position
        applyStyles()
    }
    override fun sText(text: String): DefaultMaterialButton = apply {
        this.text = text
        super.setText(this.text)}
    override fun sTextColor(color: Int): DefaultMaterialButton = apply {
        setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, color)))
        applyStyles()
    }
    override fun sTextSize(size: Int): DefaultMaterialButton = apply {
        setTextSize(size.toFloat())
        applyStyles()
    }
    override fun sTypeface(typeface: Typeface): DefaultMaterialButton = apply {
        this.typeface = typeface
    }

    fun setIcon(icon: android.graphics.drawable.Drawable?, iconSize: Int, iconTint: ColorStateList, position: ButtonIcon.IconPosition = this.iconPosition) {
        iconDrawable = icon
        iconDrawable?.let {
            buttonIcon.applyIcon(this, it, iconSize, iconTint, position)
        } ?: buttonIcon.removeIcon(this)
    }

    override fun setTextColor(colorStateList: ColorStateList) {
        customTextColor = colorStateList
        super.setTextColor(customTextColor!!)
    }

    override fun setTextSize(size: Float) {
        customTextSize = size
        super.setTextSize(size)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scaleAnimator?.cancel()
        scaleAnimator = null
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}