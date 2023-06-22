package ar.edu.itba.homewizard

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.homewizard.ui.theme.HomeWizardMobileTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import ar.edu.itba.homewizard.ui.BottomBar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var receiver: SkipNotificationReceiver
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "PermissionLaunchedDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWizardMobileTheme {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val permissionState =
                        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                    if (!permissionState.hasPermission) {
                        NotificationPermission(permissionState = permissionState)
                        LaunchedEffect(true){
                            permissionState.launchPermissionRequest()
                        }
                    }
                }

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController)}
                ) {
                    RootNavGraph(navController = navController)
                }
            }
        }
        receiver = SkipNotificationReceiver()
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
        try {
            IntentFilter(MyIntent.SHOW_NOTIFICATION)
                .apply { priority = 1 }
                .also { registerReceiver(receiver, it) }
        } catch (e: Exception) {
            println("Receiver already registered")
        }
    }

    override fun onStop() {
        try {
            unregisterReceiver(receiver)
        } catch (e: Exception) {
            println("Receiver not registered")
        }
        super.onStop()
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
}
