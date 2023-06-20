package ar.edu.itba.homewizard.ui.devices.lamp


//import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.models.devices.Lamp
import ar.edu.itba.homewizard.ui.utils.ScreenSize
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LampInfo(
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val lamp = devicesUiState.currentDevice as Lamp
    val controller = rememberColorPickerController()


    BoxWithConstraints {
        if (maxWidth < maxHeight)
            LampInfoVertical(devicesViewModel, lamp, controller, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            LampInfoHorizontal(devicesViewModel, lamp, controller, if (maxWidth > ScreenSize.tabletWidth) 2f else 1f)
    }
}
