package ar.edu.itba.homewizard.ui.devices.lamp

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Lamp
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker

@Composable
fun LampInfoVertical(devicesViewModel: DevicesViewModel, lamp: Lamp, controller: ColorPickerController, multiplier: Float = 1f) {
    Column (
        modifier = Modifier
            .padding(10.dp * multiplier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        PowerButton(
            selected = lamp.status,
            modifier = Modifier
                .padding(15.dp * multiplier),
            multiplier = multiplier
        ) {
            lamp.toggle(devicesViewModel)
        }
        CustomSlider(
            value = lamp.brightness.toFloat(),
            valueRange = 0f..100f,
            onValueChangeFinished = { lamp.changeBrightness(devicesViewModel, it.toInt()) },
            title = "Intensidad",
            unit = "",
            icon = R.drawable.lightbulb_on_10,
            multiplier = multiplier
        )

        HsvColorPicker(
            modifier = Modifier.size(200.dp* multiplier).padding(top = 15.dp* multiplier),
            initialColor = Color(android.graphics.Color.parseColor("#" + lamp.color)),
            controller = controller,
            onColorChanged = { color ->
                if (color.fromUser)
                    lamp.changeColor(devicesViewModel, color.hexCode)
            }
        )
        Surface(
            modifier = Modifier
                .height(40.dp* multiplier)
                .width(80.dp* multiplier)
                .padding(top = 15.dp* multiplier),
            color = Color(android.graphics.Color.parseColor("#" + lamp.color))
        ) {}

    }
}
