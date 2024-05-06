package com.locus2.yourapps.ui.screen.appsMainScreen

import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.core.utils.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppsMainViewModel @Inject constructor(
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(AppsMainUiState())
    val uiState = _uiState.asStateFlow()

    private fun setErrorMessage(message: String?) {
        _uiState.update {
            it.copy(
                errorMessage = message ?: "Something went wrong",
                screenState = ScreenState.Error,
            )
        }
    }

    fun resetState() {
        _uiState.value = AppsMainUiState()
    }

    fun resetError() {
        _uiState.update {
            it.copy(
                errorMessage = null,
                screenState = ScreenState.Idle,
            )
        }
    }
}
