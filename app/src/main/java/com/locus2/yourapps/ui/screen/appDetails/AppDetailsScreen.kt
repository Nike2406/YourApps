package com.locus2.yourapps.ui.screen.appDetails

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.core.utils.ui.common.AppsLoadingView
import com.locus2.yourapps.core.utils.ui.preview.PreviewAllUiModes
import com.locus2.yourapps.core.utils.ui.preview.PreviewSurface
import com.locus2.yourapps.ui.screen.appDetails.model.AppDetailsModel
import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel

@Composable
fun AppDetailsScreen() {
    val viewModel = hiltViewModel<AppDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.screenState) {
        if (uiState.errorMessage != null) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.resetError()
        }
    }
    val details = remember(uiState.appDetails) {
        uiState.appDetails
    }

    when {
        uiState.screenState == ScreenState.Success && details != null -> {
            AppDetailsView(
                appDetailsModel = details,
            )
        }

        else -> {
            AppsLoadingView()
        }
    }
}

@Composable
fun AppDetailsView(
    appDetailsModel: AppDetailsModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        Image(
            modifier = Modifier
                .size(128.dp)
                .align(Alignment.CenterHorizontally),
            bitmap = appDetailsModel.app.bitmap,
            contentDescription = appDetailsModel.app.imageDescription,
        )
        CommonSpacer()
        appDetailsModel.app.name?.let { name ->
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Application name", style = MaterialTheme.typography.body2)
            Text(text = name, style = MaterialTheme.typography.body1)
        }
        CommonSpacer()
        appDetailsModel.version.let { version ->
            Text(text = "Application version", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = version, style = MaterialTheme.typography.body1)
        }
        CommonSpacer()
        appDetailsModel.app.packageName?.let { packageName ->
            if (packageName != appDetailsModel.app.name) {
                Text(text = "Package name", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = packageName, style = MaterialTheme.typography.body1)
            }
        }
        CommonSpacer()
        appDetailsModel.apkHashSum.let { apkHashSum ->
            Text(text = "Apk hash sum", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = apkHashSum, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun CommonSpacer() {
    Spacer(modifier = Modifier.height(16.dp))
}


@PreviewAllUiModes
@Composable
private fun AppsMainViewPreview() {
    PreviewSurface {
        AppDetailsView(
            appDetailsModel = AppDetailsModel(
                app = AppModel(
                    bitmap = ImageBitmap(width = 64, height = 64),
                    imageDescription = null,
                    packageName = "some.name.package",
                    name = "Some name",
                ),
                version = "1.0",
                apkHashSum = "fheiwuhf21i3h4324"
            ),
        )
    }
}
