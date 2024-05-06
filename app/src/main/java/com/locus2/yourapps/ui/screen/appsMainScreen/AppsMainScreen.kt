package com.locus2.yourapps.ui.screen.appsMainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locus2.yourapps.core.utils.ui.preview.PreviewAllUiModes
import com.locus2.yourapps.core.utils.ui.preview.PreviewSurface

@Composable
fun SearchScreen(
    navigateToDownloads: () -> Unit,
) {
    val viewModel = hiltViewModel<AppsMainViewModel>()

    SearchView(
        navigateToDownloads = navigateToDownloads,
    )
}

@Composable
fun SearchView(
    navigateToDownloads: () -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray)
        )
        Text(text = "SearchScreen", color = Color.Black)
        Button(onClick = {  }) {
            Text(text = "Search")
        }
    }
}


@PreviewAllUiModes
@Composable
private fun SearchViewPreview() {
    PreviewSurface {
        SearchView(
            navigateToDownloads = {},
        )
    }
}
