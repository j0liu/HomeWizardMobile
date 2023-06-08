package ar.edu.itba.homewizard
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(Screen.FirstScreen.route) {
            FirstScreen()
        }
        composable(Screen.SecondScreen.route) {
            SecondScreen()
        }
        composable(Screen.ThirdScreen.route) {
            ThirdScreen()
        }
    }
}