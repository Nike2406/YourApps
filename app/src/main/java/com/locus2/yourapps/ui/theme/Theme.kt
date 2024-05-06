package com.locus2.yourapps.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = CodGray,
    secondary = StormGray,
)

private val LightColorPalette = lightColors(
    primary = CodGray,
    secondary = Ash,
)

@Composable
fun YourAppsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        typography = BundleTypography,
        colors = colors,
        content = content,
    )
}
