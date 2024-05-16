package com.locus2.yourapps.data.repository

import android.content.pm.ApplicationInfo
import com.locus2.yourapps.core.utils.hashSum.HashSum
import com.locus2.yourapps.core.utils.ui.getAppName
import com.locus2.yourapps.core.utils.ui.getImageBitmap
import com.locus2.yourapps.core.utils.ui.getImageDescription
import com.locus2.yourapps.core.utils.ui.getPackageName
import com.locus2.yourapps.data.local.YourAppsPackageManager
import com.locus2.yourapps.ui.screen.appDetails.model.AppDetailsModel
import com.locus2.yourapps.ui.screen.appsMainScreen.model.AppModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PackageManagerRepository @Inject constructor(
    private val pm: YourAppsPackageManager,
    private val incription: HashSum,
) {
    suspend fun getApplications(): Flow<List<AppModel>> {
        return flow {
            emit(
                pm.getApplications().map { app ->
                    getAppModel(app)
                }
            )
        }
    }

    suspend fun getApplicationDetails(packageName: String): Flow<AppDetailsModel> {
        return flow {
            val packageInfo = pm.getPackageDetails(packageName)
            val appInfo = pm.getApplicationDetails(packageName)

            emit(
                AppDetailsModel(
                    app = getAppModel(appInfo),
                    version = packageInfo.versionName,
                    apkHashSum = // packageInfo.installLocation
                    incription.calculate(packageInfo.signatures)
                )
            )
        }
    }

    private fun getAppModel(app: ApplicationInfo): AppModel {
        return AppModel(
            bitmap = app.getImageBitmap(
                packageManager = pm.packageManager,
                size = 64,
            ),
            imageDescription = app.getImageDescription(pm.packageManager),
            packageName = app.getPackageName(),
            name = app.getAppName(packageManager = pm.packageManager),
        )
    }
}