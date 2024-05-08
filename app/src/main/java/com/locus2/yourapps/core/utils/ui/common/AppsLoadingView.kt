package com.locus2.yourapps.core.utils.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppsLoadingView() {
    Column(modifier = Modifier.fillMaxSize()) {
        ProgressLoadingIndicator(paddingValues = PaddingValues())
    }
}