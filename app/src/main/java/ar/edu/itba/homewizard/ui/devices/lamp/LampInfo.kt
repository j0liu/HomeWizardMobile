package ar.edu.itba.homewizard.ui.devices.lamp

import android.annotation.SuppressLint

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.viewmodels.LampViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.devices.Lamp
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.ui.theme.Surface
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LampInfo(
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val lamp = devicesUiState.currentDevice as Lamp
    val controller = rememberColorPickerController()

//    var lampBrightness by remember { mutableStateOf(2f) }
    Column (
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = (devicesUiState.currentDevice as Lamp).name)
        PowerButton(
            selected = lamp.status,
        ) {
            lamp.toggle(devicesViewModel)
        }
        CustomSlider(
            value = lamp.brightness.toFloat(),
//            onValueChange = { lamp.brightness = it.toInt() },
            valueRange = 0f..100f,
            onValueChangeFinished = { lamp.changeBrightness(devicesViewModel, it.toInt()) },
            title = "Intensidad",
            unit = "",
            icon = R.drawable.lightbulb_on_10
        )
//        HsvColorPicker(
//            initialColor = Color(android.graphics.Color.parseColor(lamp.color)),
//            modifier = Modifier,
//            controller = controller,
//            onColorChanged = { a ->  lamp.changeColor(devicesViewModel, a.hexCode)},
//        )
    }
}