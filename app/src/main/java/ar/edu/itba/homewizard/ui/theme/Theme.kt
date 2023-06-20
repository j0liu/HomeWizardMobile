package ar.edu.itba.homewizard.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Colors.successContainer: Color @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFFd6ffe0) else Color(0xFF269300)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    primaryVariant = TerciaryDark,
    secondary = SecondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    onSurface = onSurfaceDark,
    onError = Error
)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = Terciary,
    secondary = Secondary,
    background = Background,
    surface = Surface,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onSurface = onSurface,
    onError = Error

    /* Other default colors to override
    background = Color.White,

    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun HomeWizardMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}