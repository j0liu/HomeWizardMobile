package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.DeviceType
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfo
import ar.edu.itba.homewizard.ui.theme.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DevicesScreen(devicesViewModel: DevicesViewModel) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Column (
                    modifier = Modifier
                        .fillMaxSize()  
                        .background(Primary),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    devicesUiState.currentDevice?.let {
                        Text(it.name)
                        it.type.infoScreen.invoke()
                    }
                }
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                devicesUiState.devices.forEach { device : Device ->
                    DeviceCard(
                        device = device,
                        onClick = { deviceSelected ->
                            devicesViewModel.setCurrentDevice(deviceSelected)
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()

                            }
                        }
                    )
                }
            }
        }
    }
}

