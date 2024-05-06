package com.locus2.yourapps.core.model

sealed class ScreenState {

    data object Idle: ScreenState()
    data object Success: ScreenState()
    data object Error: ScreenState()
    data object Loading: ScreenState()
}
