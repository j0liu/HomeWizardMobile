package ar.edu.itba.homewizard.ui

import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.itba.homewizard.viewmodels.MainViewModel

@Composable
fun BottomBar(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val devicesUiState by mainViewModel.uiState.collectAsState()

    val items = listOf(
        Screen.DevicesScreen,
        Screen.RoutinesScreen,
    )

    if (devicesUiState.displayBottomBar) {
        BottomNavigation(
            modifier = Modifier.zIndex(1f)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = MaterialTheme.colors.onPrimary,
                    icon = {
                        Icon(modifier = Modifier.height(25.dp),
                            imageVector = ImageVector.vectorResource(id = item.icon),
                            contentDescription = stringResource(item.title))
                    },
                    label = { Text(text = stringResource(item.title)) },
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}
