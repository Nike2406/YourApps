package com.locus2.yourapps.ui.screen.appDetails.model

import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel

data class AppDetailsModel(
    val app: AppModel,
    val name: String,
    val version: String,
    val apkHashSum: String,
)
