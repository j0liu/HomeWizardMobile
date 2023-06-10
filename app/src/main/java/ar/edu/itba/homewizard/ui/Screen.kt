package ar.edu.itba.homewizard.ui

import androidx.annotation.DrawableRes
import ar.edu.itba.homewizard.R

sealed class Screen(val title: String, @DrawableRes val icon: Int, val route: String) {
    object DevicesScreen: Screen("Dispositivos", R.drawable.lamp, "first_screen")
    object RoutinesScreen: Screen("Rutinas", R.drawable.clock_fast, "second_screen")
    object SettingsScreen: Screen("Ajustes", R.drawable.baseline_settings_24, "third_screen")
}