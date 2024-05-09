package com.locus2.yourapps.core.utils.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable

fun Drawable.toBitmap(size: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, size, size)
    draw(canvas)

    // Resize the bitmap more aggressively if it's too large
    val maxSize = 512 // Maximum size for the bitmap
    if (size > maxSize || size > maxSize) {
        val scale = maxSize.toFloat() / Math.max(size, size)
        val matrix = Matrix()
        matrix.setScale(scale, scale)
        return Bitmap.createBitmap(bitmap, 0, 0, size, size, matrix, true)
    }

    return bitmap
}