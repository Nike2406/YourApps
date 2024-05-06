package com.locus2.yourapps.core.utils.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.locus2.yourapps.ui.theme.YourAppsTheme

@Composable
fun PreviewSurface(
    content: @Composable () -> Unit = {},
) {
    YourAppsTheme {
        Surface(
            color = MaterialTheme.colors.background,
            content = content,
        )
    }
}

@Composable
fun PreviewColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    YourAppsTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            Column(modifier = modifier, content = content)
        }
    }
}

@Composable
fun PreviewRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit = {},
) {
    YourAppsTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            Row(modifier = modifier, content = content)
        }
    }
}
