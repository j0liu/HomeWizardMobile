package ar.edu.itba.homewizard.ui

import androidx.annotation.DrawableRes
import ar.edu.itba.homewizard.R

sealed class Screen(val title: String, @DrawableRes val icon: Int, val route: String) {
    object DevicesScreen: Screen("Dispositivos", R.drawable.outline_light_24, "first_screen")
    object RoutinesScreen: Screen("Rutinas", R.drawable.outline_watch_later_24, "second_screen")
    object SettingsScreen: Screen("Ajustes", R.drawable.outline_settings_24, "third_screen")
}