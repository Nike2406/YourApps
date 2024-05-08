package com.locus2.yourapps.ui.screen.appsMainScreen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.locus2.yourapps.ui.navigation.Screen
import com.locus2.yourapps.ui.screen.appDetails.navigation.navigateToAppDetails
import com.locus2.yourapps.ui.screen.appsMainScreen.AppsMainScreen

fun NavHostController.navigateToAppsMain(
    navOptions: NavOptions? = null,
) {
    this.navigate(Screen.AppsMainScreen.route, navOptions)
}

fun NavGraphBuilder.navigateToAppsMainScreen(
    navHostController: NavHostController,
) {
    composable(route = Screen.AppsMainScreen.route) {
        AppsMainScreen(navigateToAppDetails = navHostController::navigateToAppDetails)
    }
}
