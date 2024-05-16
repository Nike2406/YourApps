package com.locus2.yourapps.ui.screen.appDetails

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.locus2.yourapps.core.model.ScreenState
import com.locus2.yourapps.core.utils.ui.common.AppsLoadingView
import com.locus2.yourapps.core.utils.ui.preview.PreviewAllUiModes
import com.locus2.yourapps.core.utils.ui.preview.PreviewSurface
import com.locus2.yourapps.ui.screen.appDetails.model.AppDetailsModel
import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel
import timber.log.Timber

@Composable
fun AppDetailsScreen() {
    val viewModel = hiltViewModel<AppDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val intent = Intent(Intent.ACTION_MAIN).also {
        it.`package` = uiState.appDetails?.app?.packageName
    }

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
                onClick = {
                    try {
                        startActivity(context, intent, null)
                    } catch (e: ActivityNotFoundException) {
                        Timber.e("AppDetailsScreen: " + e.message)
                    }
                }
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
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
    ) {
        Image(
            modifier = Modifier
                .size(128.dp)
                .align(Alignment.CenterHorizontally),
            bitmap = appDetailsModel.app.bitmap,
            contentDescription = appDetailsModel.app.imageDescription,
        )
        appDetailsModel.app.name?.let { name ->
            CommonBlock(title = "Application name", description = name)
        }
        CommonBlock(title = "Application version", description = appDetailsModel.version)
        appDetailsModel.app.packageName?.let { packageName ->
            if (packageName != appDetailsModel.app.name) {
                CommonBlock(title = "Package name", description = packageName)
            }
        }
        CommonBlock(title = "Apk hash sum", description = appDetailsModel.apkHashSum)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = onClick,
        ) {
            Text(
                text = "Launch",
                color = MaterialTheme.colors.background,
            )
        }
    }
}

@Composable
private fun CommonBlock(title: String, description: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = title, style = MaterialTheme.typography.body2)
    Spacer(modifier = Modifier.width(8.dp))
    Text(
        text = description,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
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
            onClick = {},
        )
    }
}
