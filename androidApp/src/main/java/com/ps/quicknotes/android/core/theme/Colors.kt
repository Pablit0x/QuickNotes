package com.ps.quicknotes.android.core.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.ps.quicknotes.presentation.Colors

val LightBlue = Color(Colors.LightBlue)
val LightBlueGrey = Color(Colors.LightBlueGrey)
val AccentViolet = Color(Colors.AccentViolet)
val TextBlack = Color(Colors.TextBlack)
val DarkGrey = Color(Colors.DarkGrey)
val SuperLightGrey = Color(Colors.SuperLightGrey)
val NormalGrey = Color(Colors.NormalGrey)

val DarkSurfaceStart = Color(Colors.DarkSurfaceStart)
val DarkSurfaceEnd = Color(Colors.DarkSurfaceEnd)

val YellowHex = Color(Colors.YellowHex)
val PeachHex = Color(Colors.PeachHex)
val OrangeHex = Color(Colors.OrangeHex)
val PinkRedHex = Color(Colors.PinkRedHex)
val PurpleHex = Color(Colors.PurpleHex)

val lightColors = lightColors(
    secondary = SuperLightGrey,
    onSecondary = TextBlack,
    primary = AccentViolet,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = lightColors(
    secondary = NormalGrey,
    onSecondary = Color.White,
    primary = AccentViolet,
    background = DarkGrey,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White
)