package com.locus2.yourapps.core.utils.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable

private const val BITMAP_MAX_SIZE = 512

fun Drawable.toBitmap(size: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, size, size)
    draw(canvas)

    // Resize the bitmap more aggressively if it's too large
    if (size > BITMAP_MAX_SIZE) {
        val scale = BITMAP_MAX_SIZE.toFloat() / size.coerceAtLeast(size)
        val matrix = Matrix()
        matrix.setScale(scale, scale)
        return Bitmap.createBitmap(bitmap, 0, 0, size, size, matrix, true)
    }

    return bitmap
}