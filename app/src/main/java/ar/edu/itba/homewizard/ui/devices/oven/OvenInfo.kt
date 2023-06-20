package ar.edu.itba.homewizard.ui.devices.oven

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.models.devices.Oven
import ar.edu.itba.homewizard.ui.utils.ScreenSize
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun OvenInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val oven = devicesUiState.currentDevice as Oven

    BoxWithConstraints {
        if (maxWidth < maxHeight)
            OvenInfoVertical(devicesViewModel, oven, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            OvenInfoHorizontal(devicesViewModel, oven, if (maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)
    }


}
