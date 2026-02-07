package com.example.librarytrackerapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    /*primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80*/
    // El azul brillante para botones e iconos activos
    primary = AzurePrimary,
    onPrimary = TextWhite,

    // El fondo oscuro de toda la aplicación
    background = NavyDark,
    onBackground = TextWhite,

    // El color de las "Cards" de los libros
    surface = NavyLight,
    onSurface = TextWhite,

    // Color para textos secundarios o iconos deshabilitados
    onSurfaceVariant = TextGrey,

    // Color de la barra de búsqueda (generalmente un tono más oscuro que la card)
    surfaceVariant = Color(0xFF172033)
)

private val LightColorScheme = lightColorScheme(
    /*primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40*/

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

    // El azul brillante para botones e iconos activos
    primary = AzurePrimary,
    onPrimary = TextWhite,

    // El fondo oscuro de toda la aplicación
    background = NavyDark,
    onBackground = TextWhite,

    // El color de las "Cards" de los libros
    surface = NavyLight,
    onSurface = TextWhite,

    // Color para textos secundarios o iconos deshabilitados
    onSurfaceVariant = TextGrey,

    // Color de la barra de búsqueda (generalmente un tono más oscuro que la card)
    surfaceVariant = Color(0xFF172033)
)

@Composable
fun LibraryTrackerAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Se desactiva para que tome los colores que definimos en los temas de la app
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}