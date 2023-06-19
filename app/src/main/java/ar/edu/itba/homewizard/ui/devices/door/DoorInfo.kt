package ar.edu.itba.homewizard.ui.devices.door

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.models.devices.Door
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun DoorInfo (devicesViewModel: DevicesViewModel = hiltViewModel()){

    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val door = devicesUiState.currentDevice as Door

    BoxWithConstraints {
        if (maxWidth < maxHeight)
            DoorInfoVertical(devicesViewModel, door, if(maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            DoorInfoHorizontal(devicesViewModel, door, if(maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)

    }

}