package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.itba.homewizard.data.Device
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
                                devicesUiState.scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }
            }
        }
    }
}

