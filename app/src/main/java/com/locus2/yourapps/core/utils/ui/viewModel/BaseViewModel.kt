package com.locus2.yourapps.core.utils.ui.viewModel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

@Immutable
open class BaseViewModel : ViewModel() {

    private val supervisor = SupervisorJob(viewModelScope.coroutineContext.job)

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        onExceptionHandler(e)
    }

    open fun onExceptionHandler(e: Throwable) {
        e.printStackTrace()
    }

    private val coroutineContext = (supervisor + exceptionHandler + Dispatchers.IO)

    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(context = coroutineContext, block = block)
    }

    protected fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return viewModelScope.async(context = coroutineContext, block = block)
    }

    protected fun <T> Flow<T>.launchInIO(
        scope: CoroutineScope = viewModelScope,
    ): Job {
        return scope.launch(context = coroutineContext) {
            collectLatest {}
        }
    }
}
