package com.locus2.yourapps.ui.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.locus2.yourapps.ui.navigation.Screen
import com.locus2.yourapps.ui.screen.appDetails.navigation.navigateToAppDetailsScreen
import com.locus2.yourapps.ui.screen.appsMainScreen.navigation.navigateToAppsMainScreen

@Composable
fun LaunchNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AppsMainScreen.route,
    ) {
        navigateToAppsMainScreen(
            navHostController = navHostController,
        )

        navigateToAppDetailsScreen(
            navHostController = navHostController,
        )
    }
}
