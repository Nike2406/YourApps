package com.locus2.yourapps.ui.screen.appDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.locus2.yourapps.core.utils.ui.preview.PreviewAllUiModes
import com.locus2.yourapps.core.utils.ui.preview.PreviewSurface

@Composable
fun AppDetailsScreen(
    navigateToAppsMain: () -> Unit,
) {
    val viewModel = hiltViewModel<AppDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = uiState.screenState) {

    }

    AppDetailsView(navigateToAppsMain)
}

@Composable
fun AppDetailsView(
    navigateToAppsMain: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray)
        )
        Text(text = "AppDetailsScreen", color = Color.Black)
    }
}


@PreviewAllUiModes
@Composable
private fun AppsMainViewPreview() {
    PreviewSurface {
        AppDetailsView{}
    }
}
