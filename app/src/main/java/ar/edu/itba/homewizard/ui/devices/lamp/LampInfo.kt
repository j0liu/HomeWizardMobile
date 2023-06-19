package ar.edu.itba.homewizard.ui.devices.lamp


//import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Lamp
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.devices.door.DoorInfoHorizontal
import ar.edu.itba.homewizard.ui.devices.door.DoorInfoVertical
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.ui.theme.onPrimary
import ar.edu.itba.homewizard.ui.theme.onSurface
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.github.skydoves.colorpicker.compose.HsvColorPicker
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
