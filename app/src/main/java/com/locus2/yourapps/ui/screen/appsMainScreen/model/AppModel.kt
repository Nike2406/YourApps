package com.locus2.yourapps.ui.screen.appsMainScreen.model

import androidx.compose.ui.graphics.ImageBitmap

data class AppModel(
    val bitmap: ImageBitmap,
    val imageDescription: String?,
    val packageName: String?,
)