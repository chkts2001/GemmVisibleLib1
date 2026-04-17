package com.gemminiii.library.Common

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import com.gemminiii.library.R

val commonAttrArray = intArrayOf(
    R.attr.sCornerRadius,
    R.attr.sBackgroundColor,
    R.attr.sStrokeWidth,
    R.attr.sStrokeColor,
    R.attr.sPadding
)

data class CommonAttr(
    val cornersRadius: Float,
    val backgroundColor: Int,
    val strokeWidth: Int,
    val strokeColor: Int,
    val padding: Int
)

fun Context.readCommonAttributes(attrs: AttributeSet?): CommonAttr {
    if (attrs == null) return CommonAttr(0f, Color.TRANSPARENT, 0, Color.TRANSPARENT, 6)

    val cornerRadius = obtainStyledAttributes(attrs, intArrayOf(R.attr.sCornerRadius)).use {
        it.getDimension(0, resources.getDimension(R.dimen.default_corner_radius))
    }

    val backgroundColor = obtainStyledAttributes(attrs, intArrayOf(R.attr.sBackgroundColor)).use {
        it.getColor(0, Color.TRANSPARENT)
    }

    val strokeWidth = obtainStyledAttributes(attrs, intArrayOf(R.attr.sStrokeWidth)).use {
        it.getDimensionPixelSize(0, 0)
    }

    val strokeColor = obtainStyledAttributes(attrs, intArrayOf(R.attr.sStrokeColor)).use {
        it.getColor(0, Color.TRANSPARENT)
    }

    val padding = obtainStyledAttributes(attrs, intArrayOf(R.attr.sPadding)).use {
        it.getDimensionPixelSize(0, 6)
    }

    return CommonAttr(cornerRadius, backgroundColor, strokeWidth, strokeColor, padding)
}

fun TypedArray.toCommonAttr(): CommonAttr = CommonAttr(
    cornersRadius = getDimension(0, resources.getDimension(R.dimen.default_corner_radius)),
    backgroundColor = getColor(1, Color.TRANSPARENT),
    strokeWidth = getDimensionPixelSize(2, 0),
    strokeColor = getColor(3, Color.TRANSPARENT),
    padding = getDimensionPixelSize(4, 6),
)
