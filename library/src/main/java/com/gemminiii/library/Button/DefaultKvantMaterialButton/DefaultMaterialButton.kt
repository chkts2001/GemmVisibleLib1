package com.gemminiii.library.Button.DefaultKvantMaterialButton

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.config.ButtonDrawableConfig
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonDrawable
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonIcon
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonState
import com.gemminiii.library.Button.DefaultKvantMaterialButton.core.ButtonText
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.ButtonDrawableImpl
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.ButtonStateImpl
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.IconButtonImpl
import com.gemminiii.library.Button.DefaultKvantMaterialButton.implementation.TextStyleImpl
import com.gemminiii.library.Common.commonAttrArray
import com.gemminiii.library.Common.readCommonAttributes
//import com.gemminiii.library.Common.toCommonAttr
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

    private var buttonDrawableConfig = ButtonDrawableConfig()
    private var buttonConfig = ButtonConfig()

    init {
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        attrs?.let {
                buttonDrawableConfig.apply {
                    context.obtainStyledAttributes(attrs, commonAttrArray).use { typedArray ->
                        Log.d("FactoryDebug", "1 common")
                        val commonAttr = context.readCommonAttributes(attrs)
                        // Основные параметры
                        cornerRadius = commonAttr.cornersRadius
                        backgroundColor = commonAttr.backgroundColor
                        strokeWidth = commonAttr.strokeWidth
                        strokeColor = commonAttr.strokeColor
                        padding = commonAttr.padding
                        margin = commonAttr.margin
                    }
                }
                buttonConfig.apply {
                    context.obtainStyledAttributes(it, R.styleable.DefaultListParam).apply {
                        try {
                        // Основные параметры
                        // Иконка
                        iconRes = getResourceId(R.styleable.DefaultListParam_sIcon, 0)
                        buttonConfig.iconSize = getDimensionPixelSize(
                            R.styleable.DefaultListParam_sIconSize,
                            25
                        )
                        iconTint = getResourceId(
                            R.styleable.DefaultListParam_sIconTint,
                            android.R.color.white
                        )
                        iconGravity =
                            when (getInt(R.styleable.DefaultListParam_sIconGravity, 2)) {
                                0 -> ButtonIcon.IconPosition.START
                                1 -> ButtonIcon.IconPosition.END
                                2 -> ButtonIcon.IconPosition.CENTER
                                else -> ButtonIcon.IconPosition.CENTER
                            }

                        // Текст
                        textColor = getResourceId(
                            R.styleable.DefaultListParam_sTextColor,
                            android.R.color.black
                        )
                        textSize = (getDimensionPixelSize(
                            R.styleable.DefaultListParam_sTextSize,
                            12
                        ) / resources.displayMetrics.scaledDensity).toInt()
                        applyStyles(buttonDrawableConfig, buttonConfig)

                        //val typefaceRes = getString(R.styleable.DefaultMaterialButton_buttonTypeface)
                        //textTypeface = getFont(R.styleable.DefaultMaterialButton_buttonTypeface)
                    }finally {
                        recycle()
                    }
                }
            }
        }
    }

    fun applyStyles(drawableConfig: ButtonDrawableConfig, config: ButtonConfig) {
        Log.d("FactoryDebug", "=== applyStyles START ===")
        Log.d("FactoryDebug", "Config text: ${config.text}")
        Log.d("FactoryDebug", "Config width: ${config.width}, height: ${config.height}")
        Log.d("FactoryDebug", "Button text BEFORE apply: ${this.text}")

        try {
            config.apply {
                this@DefaultMaterialButton.insetTop = this.insetTop
                this@DefaultMaterialButton.insetBottom = this.insetBottom
                // Применяем фон
                backgroundTintList = null
                val bgColor = ContextCompat.getColor(context, drawableConfig.backgroundColor!!)
                setBackgroundColor(bgColor)


                // Применяем текст
                text?.let {
                    buttonText.applyTextStyle(this@DefaultMaterialButton,
                        text,
                        ColorStateList.valueOf(ContextCompat.getColor(context, textColor)),
                        textSize,
                        textTypeface)
                }

                // Применяем иконку
                iconRes?.let {
                    buttonIcon.applyIcon(
                        this@DefaultMaterialButton,
                        ContextCompat.getDrawable(context, it),
                        dpToPx(iconSize),
                        ColorStateList.valueOf(ContextCompat.getColor(context, iconTint)),
                        iconGravity
                    )
                }

                if(text == null){
                    width = 40
                }

                // Применяем размеры
                val lp = layoutParams
                if (lp != null) {
                    Log.d("FactoryDebug", "Old size: ${lp.width}x${lp.height}")
                    lp.width = if (width == ViewGroup.LayoutParams.MATCH_PARENT) {
                        ViewGroup.LayoutParams.MATCH_PARENT
                    } else {
                        dpToPx(width)
                    }
                    lp.height = if (height == ViewGroup.LayoutParams.MATCH_PARENT) {
                        ViewGroup.LayoutParams.MATCH_PARENT
                    } else {
                        dpToPx(height)
                    }
                    (lp as? ViewGroup.MarginLayoutParams)?.setMargins(
                        dpToPx(drawableConfig.margin),  // left
                        dpToPx(drawableConfig.margin),  // top
                        dpToPx(drawableConfig.margin),  // right
                        dpToPx(drawableConfig.margin)
                    )
                    layoutParams = lp
                    Log.d("FactoryDebug", "New size: ${lp.width}x${lp.height}")
                }

                requestLayout()
                invalidate()
            }
        } catch(e: Exception) {
            Log.e("FactoryDebug", "Error: ${e.message}", e)
        }

        Log.d("FactoryDebug", "Button text AFTER apply: ${this.text}")
        Log.d("FactoryDebug", "=== applyStyles END ===")
    }

    private fun forceRedraw() {
        // Способ 1: requestLayout + invalidate
        requestLayout()
        invalidate()

        // Способ 2: обновляем LayoutParams у родителя
        (parent as? ViewGroup)?.let { parentView ->
            // ПРАВИЛЬНО: передаём this (кнопку), а не parentView
            parentView.updateViewLayout(this, layoutParams)
        }

        // Способ 3: пост-обработка для гарантии
        post {
            requestLayout()
            invalidate()
            (parent as? ViewGroup)?.let {
                it.invalidate()
            }
        }
    }

//    fun updateConfig(block: ButtonConfig.() -> Unit) {
//        buttonConfig = buttonConfig.copyWith(block)
//        applyStyles(buttonConfig)
//        forceUpdateSize()
//    }

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

    private var currentAnimator: Animator? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // Мгновенно устанавливаем scale
                scaleX = buttonState.getPressedScale()
                scaleY = buttonState.getPressedScale()

                // Принудительно вызываем перерисовку
                invalidate()

                // Используем post для отложенной анимации возврата
                postDelayed({
                    animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(150)
                        .withEndAction { invalidate() }
                        .start()
                }, 100)

                alpha = buttonState.getStateAlpha()[0]
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // Отменяем отложенную анимацию, если палец подняли раньше
                removeCallbacks(null)
                animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .withEndAction { invalidate() }
                    .start()
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
//    fun setIcon(_icon: Int?, _iconSize: Int, _iconTint: Int, _position: ButtonIcon.IconPosition = buttonConfig.iconGravity) {
//        if(_icon != null){
//            buttonConfig.apply {
//                _icon.let {
//                    iconRes = it
//                    iconSize = dpToPx(_iconSize)
//                    iconTint = _iconTint
//                    iconGravity = _position
//                    applyStyles(this)
//                }
//            }
//
//        }
//    }

//    fun setTextParams(_text: String?, size: Int?, color: Int?, tf: Typeface?){
//        if(text != null) {
//            buttonConfig.apply {
//                text = _text
//                super.setText(text)
//                if (size != null) setTextSize(size.toFloat())
//                if (color != null) {
//                    setTextColor(
//                        ColorStateList.valueOf(
//                            if (color.toString().startsWith("0x") || color > 0xFFFFFF) color
//                            else ContextCompat.getColor(context, color)
//                        )
//                    )
//                }
//                if (tf != null) textTypeface = tf
//                applyStyles(this)
//            }
//        }
//    }

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