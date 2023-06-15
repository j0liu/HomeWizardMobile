package ar.edu.itba.homewizard.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ar.edu.itba.homewizard.R

sealed class Screen(@StringRes val title: Int, @DrawableRes val icon: Int, val route: String) {
    object DevicesScreen: Screen(R.string.devices, R.drawable.lamp, "first_screen")
    object RoutinesScreen: Screen(R.string.routines, R.drawable.clock_fast, "second_screen")
    object SettingsScreen: Screen(R.string.settings, R.drawable.baseline_settings_24, "third_screen")
}