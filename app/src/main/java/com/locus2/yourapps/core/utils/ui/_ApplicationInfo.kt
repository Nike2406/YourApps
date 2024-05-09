package com.locus2.yourapps.core.utils.ui

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun ApplicationInfo.getImageBitmap(packageManager: PackageManager, size: Int): ImageBitmap {
    return this.loadIcon(packageManager)
        .toBitmap(size = size)
        .asImageBitmap()
}

fun ApplicationInfo.getImageDescription(packageManager: PackageManager): String? {
    return this.loadDescription(packageManager)
        ?.toString()
}

fun ApplicationInfo.getPackageName(): String? {
    return this.packageName
}

fun ApplicationInfo.getAppName(packageManager: PackageManager): String? {
    return if (this.labelRes != 0) {
        loadLabel(packageManager).toString()
    } else {
        // For any case
        this.getPackageName()
    }
}