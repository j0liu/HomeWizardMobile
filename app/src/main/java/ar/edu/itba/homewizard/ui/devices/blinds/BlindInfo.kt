package ar.edu.itba.homewizard.ui.devices.blinds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.Secondary
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Blind
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import kotlinx.coroutines.delay

@Composable
fun BlindInfo(
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val blind = devicesUiState.currentDevice as Blind
    val scope  = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while(true) {
            devicesViewModel.updateDevice(blind.id, scope)
            delay(500)
        }
    }

    BoxWithConstraints {
        if (maxWidth < maxHeight) {
            BlindInfoVertical(devicesViewModel, blind, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        } else {
            BlindInfoHorizontal(devicesViewModel, blind, if (maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)
        }
    }
}