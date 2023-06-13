package ar.edu.itba.homewizard
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.homewizard.ui.devices.DevicesScreen
import ar.edu.itba.homewizard.ui.routines.RoutinesScreen
import ar.edu.itba.homewizard.ui.Screen
import ar.edu.itba.homewizard.ui.settings.SettingsScreen
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DevicesScreen.route
    ) {
        composable(Screen.DevicesScreen.route) {
            DevicesScreen(DevicesViewModel((LocalContext.current.applicationContext as HomeWizardApplication).deviceRepository))
        }
        composable(Screen.RoutinesScreen.route) {
            RoutinesScreen(RoutinesViewModel((LocalContext.current.applicationContext as HomeWizardApplication).routineRepository))
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}