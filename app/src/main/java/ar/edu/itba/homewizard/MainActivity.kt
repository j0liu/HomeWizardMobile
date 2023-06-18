package ar.edu.itba.homewizard

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.homewizard.ui.theme.HomeWizardMobileTheme
import ar.edu.itba.homewizard.ui.Screen
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import android.Manifest
import androidx.annotation.RequiresApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var receiver: SkipNotificationReceiver
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "PermissionLaunchedDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide() // TODO: Sacar
        setContent {
            HomeWizardMobileTheme {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val permissionState =
                        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                    NotificationPermission(permissionState = permissionState)
                    permissionState.launchPermissionRequest()
                }

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController)}
                ) {
                    RootNavGraph(navController = navController)
                }
            }
        }
        receiver = SkipNotificationReceiver(DEVICE_ID)
        IntentFilter(MyIntent.SHOW_NOTIFICATION)
            .apply { priority = 1 }
            .also { registerReceiver(receiver, it) }
    }

    override fun onPause() {
        super.onPause()
        getSharedPreferences("ar.edu.itba.homewizard", MODE_PRIVATE).edit().putBoolean("background", true).apply()
    }

    override fun onResume() {
        super.onResume()
        getSharedPreferences("ar.edu.itba.homewizard", MODE_PRIVATE).edit().putBoolean("background", false).apply()
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(receiver)
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun NotificationPermission(
        permissionState: PermissionState,
    ) {
        PermissionRequired(
            permissionState = permissionState,
            permissionNotGrantedContent = { /* TODO: función para infromarle al usuario de la necesidad de otrogar el permiso */ },
            permissionNotAvailableContent = { /* TODO: función hacer las adecuaciones a la App debido a que el permiso no fue otorgado  */ }
        ) {
            /* Hacer uso del recurso porque el permiso fue otorgado */
        }
    }

    companion object {
        // TODO: valor fijo, cambiar por un valor de dispositivo válido.
        private const val DEVICE_ID = "09bc8ad1f87d25c3"
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.DevicesScreen,
        Screen.RoutinesScreen,
    )

    BottomNavigation(
        modifier = Modifier.zIndex(1f)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                icon = { Icon(modifier = Modifier.height(25.dp), imageVector = ImageVector.vectorResource(id = item.icon), contentDescription = stringResource(item.title)) },
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