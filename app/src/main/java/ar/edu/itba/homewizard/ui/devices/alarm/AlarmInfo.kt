package ar.edu.itba.homewizard.ui.devices.alarm

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.models.devices.Alarm
import ar.edu.itba.homewizard.ui.utils.ScreenSize
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun AlarmInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val alarm = devicesUiState.currentDevice as Alarm
    BoxWithConstraints {
        if(maxWidth < maxHeight)
            AlarmMultiplier(devicesViewModel, alarm, if (maxHeight > ScreenSize.tabletHeight) 1.5f else 1f)
        else
            AlarmMultiplier(devicesViewModel, alarm, if (maxWidth > ScreenSize.tabletWidth) 1.5f else 1f)
    }
}
