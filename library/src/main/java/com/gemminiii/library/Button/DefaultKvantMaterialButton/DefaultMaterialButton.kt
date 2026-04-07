package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.gemminiii.library.Button.DefaultKvantMaterialButton.builder.DefaultButtonBuilder
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
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

    private var iconPadding: Int = 0

    private var customTextColor: ColorStateList? = null
    private var customTextSize: Float = 12f
    private var textColor = R.color.white
    private var customTypeface: Typeface? = null

    private var scaleAnimator: ObjectAnimator? = null

    private var buttonConfig = ButtonConfig()

    init {
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.DefaultMaterialButton).apply {
                try {
                    buttonConfig.apply {
                        // Основные параметры
                        cornerRadius = getDimension(
                            R.styleable.DefaultMaterialButton_buttonCornerRadius,
                            resources.getDimension(R.dimen.default_corner_radius)
                        )
                        backgroundColor = getColor(
                            R.styleable.DefaultMaterialButton_buttonBackgroundColor,
                            Color.TRANSPARENT
                        )
                        strokeWidth = getDimensionPixelSize(
                            R.styleable.DefaultMaterialButton_buttonStrokeWidth,
                            0
                        )
                        strokeColor = getColor(
                            R.styleable.DefaultMaterialButton_buttonStrokeColor,
                            Color.TRANSPARENT
                        )
                        padding = getDimensionPixelSize(
                            R.styleable.DefaultMaterialButton_buttonPadding,
                            6
                        )

                        // Иконка
                        iconRes = getResourceId(R.styleable.DefaultMaterialButton_buttonIcon, 0)
                        buttonConfig.iconSize = getDimensionPixelSize(
                            R.styleable.DefaultMaterialButton_buttonIconSize,
                            25
                        )
                        iconTint = getResourceId(R.styleable.DefaultMaterialButton_buttonIconTint, android.R.color.white)
                        iconGravity =
                            when (getInt(R.styleable.DefaultMaterialButton_buttonIconGravity, 2)) {
                                0 -> ButtonIcon.IconPosition.START
                                1 -> ButtonIcon.IconPosition.END
                                2 -> ButtonIcon.IconPosition.CENTER
                                else -> ButtonIcon.IconPosition.CENTER
                            }

                        // Текст
                        textColor = getResourceId(R.styleable.DefaultMaterialButton_buttonTextColor, android.R.color.black)
                        textSize = (getDimensionPixelSize(R.styleable.DefaultMaterialButton_buttonTextSize, 12) / resources.displayMetrics.scaledDensity).toInt()
                        applyStyles(buttonConfig)

                        //val typefaceRes = getString(R.styleable.DefaultMaterialButton_buttonTypeface)
                        //textTypeface = getFont(R.styleable.DefaultMaterialButton_buttonTypeface)
                    }
                } finally {
                    recycle()
                }
            }
        }
    }

    fun applyStyles(config: ButtonConfig) {
        try {
            config.apply {
                // Применяем фон
                backgroundTintList = null
                super.background = buttonDrawable.createBackground(
                    cornerRadius,
                    ContextCompat.getColor(context, backgroundColor!!),
                strokeWidth,
                ContextCompat.getColor(context, strokeColor)
                )
                val pxPadding = padding
                //super.setPadding( pxPadding, 0, pxPadding, 0)
                super.setPadding(pxPadding, 0, pxPadding, 0)
                text?.let {
                    buttonText.applyTextStyle(this@DefaultMaterialButton,
                        text,
                        ColorStateList.valueOf(ContextCompat.getColor(context, textColor)),
                        textSize,
                        textTypeface)
                }
                Log.d("_lib_", "$iconSize")
                iconRes?.let {
                    buttonIcon.applyIcon(
                        this@DefaultMaterialButton,
                        ContextCompat.getDrawable(context, it),
                        dpToPx(iconSize),
                        ColorStateList.valueOf(ContextCompat.getColor(context, iconTint)),
                        iconGravity
                    )
                }
                setSizeParams(width, height)
//                requestLayout()
//                invalidate()
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    fun updateConfig(block: ButtonConfig.() -> Unit) {
        buttonConfig = buttonConfig.copyWith(block)
        applyStyles(buttonConfig)
        forceUpdateSize()
    }

    private fun forceUpdateSize() {
        val parent = parent as? ViewGroup

        // Получаем новые размеры
        val newWidth = convertSize(buttonConfig.width)
        val newHeight = convertSize(buttonConfig.height)

        Log.d("SizeDebug", "Force update: $newWidth x $newHeight")

        // Способ 1: Прямое изменение
        val lp = LinearLayout.LayoutParams(newWidth, newHeight)

        // Копируем margins
        (layoutParams as? LinearLayout.LayoutParams)?.let { old ->
            lp.setMargins(old.leftMargin, old.topMargin, old.rightMargin, old.bottomMargin)
        }

        // Устанавливаем
        layoutParams = lp

        // Способ 2: Через родителя
        parent?.updateViewLayout(this, lp)

        // Способ 3: Принудительное изменение размеров
        setMinimumWidth(if (newWidth > 0) newWidth else 0)
        setMinimumHeight(if (newHeight > 0) newHeight else 0)

        // Способ 4: Перерисовка
        requestLayout()
        invalidate()

        // Способ 5: Отложенная перерисовка
        post {
            requestLayout()
            invalidate()

            // Способ 6: Пересоздание View
            parent?.let { p ->
                val index = p.indexOfChild(this)
                p.removeView(this)
                p.addView(this, index, lp)
            }
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

    fun setSizeParams(width: Int, height: Int){
        val lp = layoutParams

        if (lp == null) {
            // Создаем новые параметры
            val newLp = LinearLayout.LayoutParams(
                convertSize(width),
                convertSize(height)
            )
            layoutParams = newLp
        } else {
            // Обновляем существующие
            var needsUpdate = false

            val newWidth = convertSize(width)
            val newHeight = convertSize(height)

            if (lp.width != newWidth) {
                lp.width = newWidth
                needsUpdate = true
            }

            if (lp.height != newHeight) {
                lp.height = newHeight
                needsUpdate = true
            }

            if (needsUpdate) {
                layoutParams = lp
            }
        }
        (parent as? ViewGroup)?.let { parentView ->
            parentView.updateViewLayout(this, layoutParams)
            parentView.requestLayout()
            parentView.invalidate()
        }

        // Принудительно запрашиваем перерисовку
        post {
            requestLayout()
            invalidate()
        }
    }
    private fun convertSize(size: Int): Int {
        return when (size) {
            LinearLayout.LayoutParams.MATCH_PARENT ->  LinearLayout.LayoutParams.MATCH_PARENT
            LinearLayout.LayoutParams.WRAP_CONTENT ->  LinearLayout.LayoutParams.WRAP_CONTENT
            else -> {
                if (size > 0) dpToPx(size) else  LinearLayout.LayoutParams.WRAP_CONTENT
            }
        }
    }
    fun setIcon(_icon: Int?, _iconSize: Int, _iconTint: Int, _position: ButtonIcon.IconPosition = buttonConfig.iconGravity) {
        if(_icon != null){
            buttonConfig.apply {
                _icon.let {
                    iconRes = it
                    iconSize = dpToPx(_iconSize)
                    iconTint = _iconTint
                    iconGravity = _position
                    applyStyles(this)
                }
            }

        }
    }

    fun setTextParams(_text: String?, size: Int?, color: Int?, tf: Typeface?){
        if(text != null) {
            buttonConfig.apply {
                text = _text
                super.setText(text)
                if (size != null) setTextSize(size.toFloat())
                if (color != null) {
                    setTextColor(
                        ColorStateList.valueOf(
                            if (color.toString().startsWith("0x") || color > 0xFFFFFF) color
                            else ContextCompat.getColor(context, color)
                        )
                    )
                }
                if (tf != null) textTypeface = tf
                applyStyles(this)
            }
        }
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