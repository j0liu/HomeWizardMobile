package ar.edu.itba.homewizard.ui.devices.lamp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Lamp
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.PowerButton
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

//    var lampBrightness by remember { mutableStateOf(2f) }
    Column (
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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