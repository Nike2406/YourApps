package com.locus2.yourapps.ui.screen.appDetails

import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.ui.screen.appDetails.model.AppDetailsModel

data class AppDetailsUiState(
    val screenState: ScreenState = ScreenState.Idle,
    val errorMessage: String? = null,

    val appDetails: AppDetailsModel? = null,
)
