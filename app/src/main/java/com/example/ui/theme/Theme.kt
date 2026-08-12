package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    primary = primary,
    surfaceVariant = surfaceContainerHigh,
    outlineVariant = outlineVariant,
    onSurfaceVariant = onSurfaceVariant,
    secondaryContainer = secondaryContainer,
    secondary = secondary,
    surfaceContainer = surfaceContainerLow,
    surfaceContainerHigh = surfaceContainerHigh,
    surfaceContainerHighest = surfaceContainerHighest
  )

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit,
) {
  MaterialTheme(colorScheme = DarkColorScheme, typography = Typography, content = content)
}
