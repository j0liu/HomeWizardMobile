package ar.edu.itba.homewizard.ui.devices.refrigerator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Fridge
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfoVertical
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun RefrigeratorInfo (devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val fridge = devicesUiState.currentDevice as Fridge

    BoxWithConstraints {
        if(maxWidth < maxHeight)
            RefrigeratorMultiplier(devicesViewModel, fridge, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            RefrigeratorMultiplier(devicesViewModel, fridge, if (maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)
    }

}