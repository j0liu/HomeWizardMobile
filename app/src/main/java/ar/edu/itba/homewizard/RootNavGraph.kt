package ar.edu.itba.homewizard
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.homewizard.ui.devices.DevicesScreen
import ar.edu.itba.homewizard.ui.routines.RoutinesScreen
import ar.edu.itba.homewizard.ui.Screen
import ar.edu.itba.homewizard.ui.settings.SettingsScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DevicesScreen.route
    ) {
        composable(Screen.DevicesScreen.route) {
            DevicesScreen()
        }
        composable(Screen.RoutinesScreen.route) {
            RoutinesScreen()
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}