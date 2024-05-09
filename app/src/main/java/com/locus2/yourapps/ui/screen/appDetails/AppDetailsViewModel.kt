package com.locus2.yourapps.ui.screen.appDetails

import androidx.lifecycle.SavedStateHandle
import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.core.utils.constant.DEFAULT_ERROR_MESSAGE
import com.locus2.yourapps.core.utils.ui.viewModel.BaseViewModel
import com.locus2.yourapps.data.repository.PackageManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val packageManagerRepository: PackageManagerRepository,
) : BaseViewModel() {

    val packageName: String = checkNotNull(savedStateHandle[APP_PACKAGE_NAME])

    private val _uiState = MutableStateFlow(AppDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launch {
            updateAppDetails()
        }
    }
    private suspend fun updateAppDetails() {
        setLoadingState()
        packageManagerRepository.getApplicationDetails(packageName)
            .catch {
                Timber.e("updateAppDetails: " + it.message)
                setErrorMessage()
            }
            .collectLatest { app ->
                _uiState.update {
                    it.copy(
                        screenState = ScreenState.Success,
                        appDetails = app,
                    )
                }
            }
    }

    private fun setErrorMessage(message: String? = null) {
        _uiState.update {
            it.copy(
                errorMessage = message ?: DEFAULT_ERROR_MESSAGE,
                screenState = ScreenState.Error,
            )
        }
    }

    private fun setLoadingState() {
        _uiState.update {
            it.copy(screenState = ScreenState.Loading)
        }
    }

    fun resetError() {
        _uiState.update {
            it.copy(
                errorMessage = null,
                screenState = ScreenState.Idle,
            )
        }
    }


    companion object {

        const val APP_PACKAGE_NAME = "app_package_name"
    }
}
