package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DevicesHorizontal (mainViewModel: MainViewModel, devicesViewModel: DevicesViewModel)  {
    val scope = rememberCoroutineScope()
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val mainUiState by mainViewModel.uiState.collectAsState()

    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(devicesUiState.devices.toMutableList(), key = { device: Device -> device.id }) { device : Device ->
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