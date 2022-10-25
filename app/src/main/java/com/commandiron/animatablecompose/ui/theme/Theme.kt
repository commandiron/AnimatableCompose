package com.commandiron.animatablecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    surfaceVariant = MaximumGreenYellow,
    onSurfaceVariant = CosmicLatte,
    background = CosmicLatte
)

private val LightColorScheme = lightColorScheme(
    surfaceVariant = MaximumGreenYellow,
    onSurfaceVariant = CosmicLatte,
    background = CosmicLatte
)

@Composable
fun AnimatableComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}