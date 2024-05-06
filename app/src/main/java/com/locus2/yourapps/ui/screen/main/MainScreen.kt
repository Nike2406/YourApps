package com.locus2.yourapps.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.locus2.yourapps.ui.navigation.graph.LaunchNavGraph

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) { paddingValues ->
        LaunchNavGraph(
            paddingValues = paddingValues,
            navHostController = navHostController,
        )
    }
}
