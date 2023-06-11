package ar.edu.itba.homewizard.ui.devices.lamp

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
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.viewmodels.LampViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.theme.Surface
import io.mhssn.colorpicker.ColorPicker
import io.mhssn.colorpicker.ColorPickerType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LampInfo(lampViewModel: LampViewModel = viewModel()) {
    var lampBrightness by remember { mutableStateOf(2f) }
    Column (
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //rounded button with material design icon
        IconButton(
            onClick = {  },
            modifier = Modifier
                .size(160.dp)
                .background(Surface, shape = CircleShape)
        ) {
            Icon(
                modifier = Modifier.size(80.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_power_settings_new_24),
                contentDescription = "content description"
            )
        }
        CustomSlider(
            value = lampBrightness,
            onValueChange = { lampBrightness = it },
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