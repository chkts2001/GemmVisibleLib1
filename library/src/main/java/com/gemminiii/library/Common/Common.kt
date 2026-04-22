package com.gemminiii.library.Common

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.gemminiii.library.R

val commonAttrArray = intArrayOf(
    R.attr.sCornerRadius,
    R.attr.sBackgroundColor,
    R.attr.sStrokeWidth,
    R.attr.sStrokeColor,
    R.attr.sCommonPadding,
    R.attr.sCommonMargin,
    R.attr.sCommonColor
)

data class CommonAttr(
    val commonColor: Int,
    val cornersRadius: Float,
    val backgroundColor: Int,
    val strokeWidth: Int,
    val strokeColor: Int,
    val padding: Int,
    val margin: Int,
)

fun Context.readCommonAttributes(attrs: AttributeSet?): CommonAttr {
    if (attrs == null) return CommonAttr(0,0f, android.R.color.transparent, 0, android.R.color.transparent, 6, 0)

    return obtainStyledAttributes(attrs, intArrayOf(
        R.attr.sCommonColor,
        R.attr.sCornerRadius,
        R.attr.sBackgroundColor,
        R.attr.sStrokeWidth,
        R.attr.sStrokeColor,
        R.attr.sCommonPadding,
        R.attr.sCommonMargin
    )).use { typedArray ->

        val commonColor = typedArray.getResourceId(0, 0)
        val cornerRadius =
            typedArray.getDimension(1, resources.getDimension(R.dimen.default_corner_radius))
        val backgroundColor = typedArray.getResourceId(2, android.R.color.transparent)
        val strokeWidth = typedArray.getDimensionPixelSize(3, 0)
        val strokeColor =
            typedArray.getResourceId(4, commonColor) // commonColor как значение по умолчанию
        val padding = typedArray.getDimensionPixelSize(5, 3)
        val margin = typedArray.getDimensionPixelSize(6, 3)

        Log.d("FactoryDebug", "commonColor: $commonColor")
        Log.d("FactoryDebug", "strokeColor: $strokeColor (using commonColor as default)")

        CommonAttr(
            commonColor = commonColor,
            cornersRadius = cornerRadius,
            backgroundColor = backgroundColor,
            strokeWidth = strokeWidth,
            strokeColor = strokeColor,
            padding = padding,
            margin = margin
        )
    }

//    val commonColor = obtainStyledAttributes(attrs, intArrayOf(R.attr.sCommonColor)).use{
//        it.getResourceId(0, 0)
//    }
//
//    val cornerRadius = obtainStyledAttributes(attrs, intArrayOf(R.attr.sCornerRadius)).use {
//        it.getDimension(0, resources.getDimension(R.dimen.default_corner_radius))
//    }
//
//    val backgroundColor = obtainStyledAttributes(attrs, intArrayOf(R.attr.sBackgroundColor)).use {
//        it.getResourceId(0, Color.TRANSPARENT)
//    }
//
//    val strokeWidth = obtainStyledAttributes(attrs, intArrayOf(R.attr.sStrokeWidth)).use {
//        it.getDimensionPixelSize(0, 0)
//    }
//
//    val strokeColor = obtainStyledAttributes(attrs, intArrayOf(R.attr.sStrokeColor)).use {
//        Log.d("FactoryDebug", "commonColor in common: ${commonColor}")
//        it.getResourceId(0, commonColor)
//    }
//
//    val padding = obtainStyledAttributes(attrs, intArrayOf(R.attr.sPadding)).use {
//        it.getDimensionPixelSize(0, 6)
//    }
//
//    val margin = obtainStyledAttributes(attrs, intArrayOf(R.attr.sMargin)).use{
//        it.getDimensionPixelSize(0, 0)
//    }
//
//    return CommonAttr(commonColor,cornerRadius, backgroundColor, strokeWidth, strokeColor, padding, margin)
}

//fun TypedArray.toCommonAttr(): CommonAttr = CommonAttr(
//    cornersRadius = getDimension(0, resources.getDimension(R.dimen.default_corner_radius)),
//    backgroundColor = getColor(1, Color.TRANSPARENT),
//    strokeWidth = getDimensionPixelSize(2, 0),
//    strokeColor = getColor(3, Color.TRANSPARENT),
//    padding = getDimensionPixelSize(4, 6),
//    margin = getDimensionPixelSize(5, 0)
//)
