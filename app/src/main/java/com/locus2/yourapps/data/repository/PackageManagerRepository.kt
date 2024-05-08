package com.locus2.yourapps.data.repository

import android.content.pm.ApplicationInfo
import com.locus2.yourapps.core.utils.ui.getAppName
import com.locus2.yourapps.core.utils.ui.getImageBitmap
import com.locus2.yourapps.core.utils.ui.getImageDescription
import com.locus2.yourapps.data.local.YourAppsPackageManager
import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PackageManagerRepository @Inject constructor(
    private val pm: YourAppsPackageManager,
) {
    suspend fun getApplications(): Flow<List<AppModel>> {
        return flow {
            emit(
                pm.getApplications().map { app ->
                    AppModel(
                        bitmap = app.getImageBitmap(
                            packageManager = pm.packageManager,
                            size = 64
                        ),
                        imageDescription = app.getImageDescription(pm.packageManager),
                        packageName = app.getAppName(packageManager = pm.packageManager)
                    )
                }
            )
        }
    }

    suspend fun getApplicationDetails(packageName: String): Flow<ApplicationInfo> {
        return flow { emit(pm.getApplicationDetails(packageName)) }
    }
}