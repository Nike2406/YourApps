package com.locus2.yourapps.data.local

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class YourAppsPackageManager @Inject constructor(
    @ApplicationContext val context: Context,
) {

    val packageManager: PackageManager = context.packageManager

    fun getApplications(): List<ApplicationInfo> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(0L))
        } else {
            packageManager.getInstalledApplications(0)
        }
    }

    fun getApplicationDetails(packageName: String): ApplicationInfo {
        return packageManager.getApplicationInfo(packageName, 0)
    }

    // TODO Deprecated
    fun getPackageDetails(packageName: String): PackageInfo {
        return packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
    }
}
