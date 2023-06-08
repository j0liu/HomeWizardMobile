package ar.edu.itba.homewizard

import androidx.annotation.DrawableRes


sealed class Screen(val title: String, @DrawableRes val icon: Int, val route: String) {
    object FirstScreen: Screen("Dispositivos", R.drawable.outline_light_24, "first_screen")
    object SecondScreen: Screen("Rutinas", R.drawable.outline_watch_later_24, "second_screen")
    object ThirdScreen: Screen("Ajustes", R.drawable.outline_settings_24, "third_screen")
}