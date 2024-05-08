package com.locus2.yourapps.ui.screen.appDetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.locus2.yourapps.ui.navigation.Screen
import com.locus2.yourapps.ui.screen.appDetails.AppDetailsScreen

fun NavHostController.navigateToAppDetails(
    navOptions: NavOptions? = null,
) {
    this.navigate(Screen.AppDetailsScreen.route, navOptions)
}

fun NavGraphBuilder.navigateToAppDetailsScreen(
    navHostController: NavHostController,
) {
    composable(route = Screen.AppDetailsScreen.route) {
        AppDetailsScreen(navigateToAppsMain = navHostController::navigateUp)
    }
}
