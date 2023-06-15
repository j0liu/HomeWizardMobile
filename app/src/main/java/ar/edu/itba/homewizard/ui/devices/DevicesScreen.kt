package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.ui.theme.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DevicesScreen(devicesViewModel: DevicesViewModel) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    // TODO: Modularizar?
    BackHandler(enabled = devicesUiState.scaffoldState.bottomSheetState.isExpanded) {
        scope.launch {
            devicesUiState.scaffoldState.bottomSheetState.collapse()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        BottomSheetScaffold(
            sheetShape = RoundedCornerShape(15.dp),
            scaffoldState = devicesUiState.scaffoldState,
            sheetContent = {
                DeviceInfo(devicesViewModel = devicesViewModel)
            }
        ) {
            LazyColumn (
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = devicesUiState.devices.toMutableList(), key = { device: Device -> device.id }) { device : Device ->
                    DeviceCard(
                        device = device,
                        onClick = { deviceSelected ->
                            devicesViewModel.setCurrentDevice(deviceSelected)
                            scope.launch {
                                devicesUiState.scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }
            }
        }
    }
}


