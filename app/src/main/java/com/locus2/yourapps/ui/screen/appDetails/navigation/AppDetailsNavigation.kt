package com.locus2.yourapps.ui.screen.appDetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.locus2.yourapps.ui.navigation.Screen
import com.locus2.yourapps.ui.screen.appDetails.AppDetailsScreen
import com.locus2.yourapps.ui.screen.appDetails.AppDetailsViewModel

fun NavHostController.navigateToAppDetails(
    navOptions: NavOptions? = null,
    packageName: String,
) {
    this.navigate(
        route = Screen.AppDetailsScreen.getParams(packageName),
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.navigateToAppDetailsScreen() {
    composable(
        route = Screen.AppDetailsScreen.route,
        arguments = listOf(
            navArgument(AppDetailsViewModel.APP_PACKAGE_NAME) { type = NavType.StringType },
        )
    ) {
        AppDetailsScreen()
    }
}
