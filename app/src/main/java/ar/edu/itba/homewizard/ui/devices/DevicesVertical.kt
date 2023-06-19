package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DevicesVertical(mainViewModel: MainViewModel, devicesViewModel: DevicesViewModel) {
    val scope = rememberCoroutineScope()
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val mainUiState by mainViewModel.uiState.collectAsState()

    LazyColumn (
        modifier = Modifier
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = devicesUiState.devices.toMutableList(), key = { device: Device -> device.id }) { device : Device ->
            DeviceCard(
                device = device,
                onClick = { deviceSelected ->
                    devicesViewModel.setCurrentDevice(deviceSelected)
                    mainViewModel.setBottomBarVisibility(false)
                    scope.launch {
                        mainUiState.scaffoldState.bottomSheetState.expand()
                    }
                }
            )
        }
    }
}
