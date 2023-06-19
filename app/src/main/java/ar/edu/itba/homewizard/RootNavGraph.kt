package ar.edu.itba.homewizard
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.homewizard.ui.devices.DevicesScreen
import ar.edu.itba.homewizard.ui.routines.RoutinesScreen
import ar.edu.itba.homewizard.ui.Screen
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DevicesScreen.route
    ) {
        composable(Screen.DevicesScreen.route) {
            DevicesScreen(mainViewModel = mainViewModel)
        }
        composable(Screen.RoutinesScreen.route) {
            RoutinesScreen(mainViewModel = mainViewModel)
        }
    }
}