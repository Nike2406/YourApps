package com.locus2.yourapps.ui.navigation

sealed class Screen(val route: String) {

    data object AppsMainScreen : Screen(route = APPS_MAIN)
    data object AppDetailsScreen : Screen(route = "$APP_DETAILS/$APP_PACKAGE_NAME") {
        fun getParams(packageName: String): String {
            return "$APP_DETAILS/$packageName"
        }
    }


    companion object {

        const val APPS_MAIN = "apps_main"
        const val APP_DETAILS = "app_details"

        const val APP_PACKAGE_NAME = "{app_package_name}"
    }
}
