package ar.edu.itba.homewizard.ui.devices.refrigerator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.models.devices.Fridge
import ar.edu.itba.homewizard.ui.utils.ScreenSize
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import kotlinx.coroutines.delay

@Composable
fun RefrigeratorInfo (devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val fridge = devicesUiState.currentDevice as Fridge
    val scope  = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while(true) {
            devicesViewModel.updateDevice(fridge.id, scope)
            delay(500)
        }
    }

    BoxWithConstraints {
        if(maxWidth < maxHeight)
            RefrigeratorMultiplier(devicesViewModel, fridge, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            RefrigeratorMultiplier(devicesViewModel, fridge, if (maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)
    }

}