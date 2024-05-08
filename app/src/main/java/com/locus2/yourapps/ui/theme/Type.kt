package com.locus2.yourapps.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.locus2.yourapps.R

val Inter = FontFamily(
    Font(R.font.inter_light, FontWeight.Light), // 300
    Font(R.font.inter_regular, FontWeight.Normal), // 400
    Font(R.font.inter_medium, FontWeight.Medium), // 500
    Font(R.font.inter_semibold, FontWeight.SemiBold), // 600
)

val BundleTypography = Typography(
    button = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
        color = Black,
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.W900,
        fontSize = 34.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.52).sp,
        color = Black,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.W900,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.44).sp,
        color = Black,
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.24).sp,
        color = Black,
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.W800,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.24).sp,
        color = Black,
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = Black,
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.24).sp,
        color = Black,
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
        color = Black,
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        color = Black
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Black
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        color = Black
    ),
)
