package com.locus2.yourapps.ui.screen.appsMainScreen

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
class AppsMainViewModel @Inject constructor(
    private val packageManagerRepository: PackageManagerRepository,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(AppsMainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launch {
            updateAppsList()
        }
    }

    private suspend fun updateAppsList() {
        setLoadingState()
        packageManagerRepository.getApplications()
            .catch {
                Timber.e("updateAppsList: " + it.message)
                setErrorMessage()
            }
            .collectLatest { apps ->
                _uiState.update {
                    it.copy(
                        screenState = ScreenState.Success,
                        yourApps = apps,
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

    // TODO() For pull-to-refresh
    fun resetState() {
        launch {
            _uiState.value = AppsMainUiState()
            updateAppsList()
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
}
