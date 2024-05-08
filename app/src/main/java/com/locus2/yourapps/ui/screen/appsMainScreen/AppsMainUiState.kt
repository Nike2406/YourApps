package com.locus2.yourapps.ui.screen.appsMainScreen

import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel

data class AppsMainUiState(
    val screenState: ScreenState = ScreenState.Idle,
    val errorMessage: String? = null,

    val yourApps: List<AppModel> = emptyList(),
)
