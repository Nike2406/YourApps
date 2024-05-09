package com.locus2.yourapps.ui.screen.appsMainScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.core.utils.ui.common.AppsLoadingView
import com.locus2.yourapps.core.utils.ui.preview.PreviewAllUiModes
import com.locus2.yourapps.core.utils.ui.preview.PreviewSurface
import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel

@Composable
fun AppsMainScreen(
    navigateToAppDetails: (String) -> Unit,
    paddingValues: PaddingValues,
) {
    val viewModel = hiltViewModel<AppsMainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.screenState) {
        if (uiState.errorMessage != null) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.resetError()
        }
    }

    when (uiState.screenState) {
        is ScreenState.Loading -> {
            AppsLoadingView()
        }

        else -> {
            AppsMainView(
                navigateToAppDetails = navigateToAppDetails,
                apps = uiState.yourApps,
                paddingValues = paddingValues,
            )
        }
    }
}

@Composable
fun AppsMainView(
    navigateToAppDetails: (String) -> Unit,
    apps: List<AppModel>,
    paddingValues: PaddingValues,
) {
    LazyColumn(contentPadding = paddingValues) {
        items(items = apps) { app ->
            AppElement(
                app = app,
                onClick = navigateToAppDetails,
            )
        }
    }
}

@Composable
fun AppElement(
    modifier: Modifier = Modifier,
    app: AppModel,
    onClick: (String) -> Unit,
) {
    if (app.name != null && app.packageName != null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = { onClick(app.packageName) })
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                bitmap = app.bitmap,
                contentDescription = app.imageDescription,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = app.name, style = MaterialTheme.typography.body1)
        }
    }
}


@PreviewAllUiModes
@Composable
private fun AppsMainViewPreview() {
    PreviewSurface {
        AppsMainView(
            navigateToAppDetails = {},
            apps = emptyList(),
            paddingValues = PaddingValues(),
        )
    }
}
