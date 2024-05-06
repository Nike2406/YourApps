package com.locus2.yourapps.core.utils.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.locus2.yourapps.core.utils.ui.preview.PreviewAllUiModes
import com.locus2.yourapps.core.utils.ui.preview.PreviewColumn

private const val SHIMMER_LABEL = "shimmer"

fun Modifier.shimmerBackground(shape: Shape = RectangleShape): Modifier = composed {
    val transition = rememberInfiniteTransition(label = SHIMMER_LABEL)
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = SHIMMER_LABEL,
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.4f),
    )
    val brush = Brush.radialGradient(
        colors = shimmerColors,
        center = Offset(translateAnimation, translateAnimation),
        radius = 1000f,
        tileMode = TileMode.Clamp,
    )
    return@composed this.then(background(brush, shape))
}

fun Modifier.statusAndNavigationBarsPadding(): Modifier {
    return this
        .statusBarsPadding()
        .navigationBarsPadding()
}

@PreviewAllUiModes
@Composable
fun ShimmerPreview() {
    PreviewColumn {
        repeat(3) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 60.dp)
                    .shimmerBackground(RoundedCornerShape(16.dp))
            )
        }
    }
}
