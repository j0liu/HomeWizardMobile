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
import io.mhssn.colorpicker.ColorPicker
import io.mhssn.colorpicker.ColorPickerType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LampInfo(
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val fakeState = MutableStateFlow<Lamp>(devicesUiState.currentDevice as Lamp)
    val lamp by fakeState.collectAsState()
    println("hola")


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
//            lamp.status = !(lamp.status)
        }
        CustomSlider(
            value = lamp.brightness.toFloat(),
            onValueChange = { /*TODO*/ },
            valueRange = 0f..100f,
            onValueChangeFinished = { /*TODO*/ },
            title = "Intensidad",
            unit = "",
            icon = R.drawable.lightbulb_on_10
        )
        ColorPicker (
            type = ColorPickerType.Circle(
                showAlphaBar = false,
            ),
            modifier = Modifier.pointerInput(Unit) {
                detectDragGestures (
                    onDrag = { dragEnd, velocity ->
                        // Lógica para manejar el final del arrastre
                    }
                )
            }
        ){

        }
    }
}