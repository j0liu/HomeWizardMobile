package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.BottomSheetScaffold
import ar.edu.itba.homewizard.ui.devices.ac.ACInfo
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfo
import ar.edu.itba.homewizard.ui.devices.blinds.BlindInfo
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.ui.devices.refrigerator.RefrigeratorInfo
import ar.edu.itba.homewizard.ui.theme.Background
import ar.edu.itba.homewizard.ui.theme.Terciary


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DevicesScreen(devicesViewModel: DevicesViewModel = viewModel()) {
//    var devices = listOf(Device("1", "horno garage", DeviceType("1", "horno"), {}),Device("2", "aire patio", DeviceType("1", "ac"), {}))
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
                    devicesUiState.currentDevice?.let { Text(it.name) }
//                    LampInfo()
//                    RefrigeratorInfo()
                    ACInfo()
//                    AlarmInfo()
//                    ACInfo()
                    BlindInfo()
                }
            }) {
            // app UI
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //                devices.forEach { device -> DeviceCard(device = device )
                devicesUiState.devices.forEach { device : Device ->
                    DeviceCard(
                        device = device,
                        onClick = { deviceSelected ->
                            println("CACACACACA: " + deviceSelected.name)
                            devicesViewModel.setCurrentDevice(deviceSelected)
                            scope.launch { scaffoldState.bottomSheetState.expand() }
                        }
                    )
                }
                Button(
                    content = { Text("Add Device") },
                    onClick = { devicesViewModel.addDevice()}
                )
            }
        }
    }
}

