package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
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
    private var width: Int = LinearLayout.LayoutParams.MATCH_PARENT
    private var height: Int = 40
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
            super.setPadding( pxPadding, 0, pxPadding, 0)

            if (customTextColor != null) {
                super.setTextColor(customTextColor!!)
            }

            if (customTextSize > 0) {
                super.setTextSize(customTextSize)
            }

            customTypeface?.let {
                super.setTypeface(it)
            }
            Log.d("_lib_", "$iconSize")
            buttonIcon.applyIcon(this, iconDrawable, iconSize, iconTint, iconPosition)

            // Применяем иконку
            iconDrawable?.let {
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

    override fun sWidth(width: Int): DefaultMaterialButton = apply {
        this.width = width
        setSizeParams(this.width, this.height)
    }

    override fun sHeight(height: Int): DefaultMaterialButton = apply{
        this.height = height
        setSizeParams(this.width, this.height)
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
        this.buttonPadding += width
        super.setPadding( buttonPadding, 0, buttonPadding, 0)
        applyStyles()
    }

    override fun sGradient(startColor: Int, endColor: Int): DefaultMaterialButton = apply {
        this.gradientStartColor = startColor
        this.gradientEndColor = endColor
    }

    override fun sPaddings(padding: Int): DefaultMaterialButton = apply{
        this.buttonPadding = padding + strokeWidth
        super.setPadding( buttonPadding, 0, buttonPadding, 0)
        applyStyles()
    }
    override fun sIcon(_iconRes: Int, _iconSize: Int, _iconTint: Int): DefaultMaterialButton = apply {
        setIcon(_iconRes, _iconSize, _iconTint)

    }
    override fun sIconGravity(position: ButtonIcon.IconPosition): DefaultMaterialButton = apply {
        this.iconPosition = position
        applyStyles()
    }
    override fun sText(text: String?, size: Int?, color: Int?, tf: Typeface?): DefaultMaterialButton = apply {
        this.text = text
        super.setText(this.text)
        if(size != null) setTextSize(size.toFloat())
        if(color != null) {
            setTextColor(
                ColorStateList.valueOf(
                    if (color.toString().startsWith("0x") || color > 0xFFFFFF) color
                    else ContextCompat.getColor(context, color)
                )
            )
        }
        if(typeface != null)this.typeface = typeface
        applyStyles()
    }
//    override fun sTextColor(color: Int): DefaultMaterialButton = apply {
//        setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, color)))
//        applyStyles()
//    }
//    override fun sTextSize(size: Int): DefaultMaterialButton = apply {
//        setTextSize(size.toFloat())
//        applyStyles()
//    }
//    override fun sTypeface(typeface: Typeface): DefaultMaterialButton = apply {
//        this.typeface = typeface
//    }

    fun setSizeParams(width: Int, height: Int){
        Log.d("Builder", "setSizeParams called with width=$width, height=$height")

        val valueWidth = when {
            width == LinearLayout.LayoutParams.MATCH_PARENT -> {
                Log.d("Builder", "width is MATCH_PARENT")
                LinearLayout.LayoutParams.MATCH_PARENT
            }
            width == LinearLayout.LayoutParams.WRAP_CONTENT -> {
                Log.d("Builder", "width is WRAP_CONTENT")
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
            width > 0 -> {
                val px = dpToPx(width)
                Log.d("Builder", "width = $width dp -> $px px")
                px
            }
            else -> {
                Log.d("Builder", "width default to WRAP_CONTENT")
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
        }

        val valueHeight = when {
            height == LinearLayout.LayoutParams.MATCH_PARENT -> {
                Log.d("Builder", "height is MATCH_PARENT")
                LinearLayout.LayoutParams.MATCH_PARENT
            }
            height == LinearLayout.LayoutParams.WRAP_CONTENT -> {
                Log.d("Builder", "height is WRAP_CONTENT")
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
            height > 0 -> {
                val px = dpToPx(height)
                Log.d("Builder", "height = $height dp -> $px px")
                px
            }
            else -> {
                Log.d("Builder", "height default to WRAP_CONTENT")
                LinearLayout.LayoutParams.WRAP_CONTENT
            }
        }

        val layoutParams = LinearLayout.LayoutParams(valueWidth, valueHeight).apply {
            setMargins(0,0,0,0)
        }
        this.layoutParams = layoutParams

        Log.d("Builder", "Final LayoutParams set to: ${layoutParams.width} x ${layoutParams.height}")
    }
    fun setIcon(_icon: Int, _iconSize: Int, _iconTint: Int, _position: ButtonIcon.IconPosition = this.iconPosition) {
        this.iconDrawable = ContextCompat.getDrawable(context, _icon)
        this.iconSize = dpToPx(_iconSize)
        this.iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, _iconTint))
        this.iconPosition = _position
        applyStyles()
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