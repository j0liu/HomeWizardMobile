package ar.edu.itba.homewizard.ui.devices.lamp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun LampInfoVertical(devicesViewModel: DevicesViewModel, lamp: Lamp, controller: ColorPickerController) {
    Column (
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        PowerButton(
            selected = lamp.status,
            modifier = Modifier
                .padding(bottom = 15.dp)
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
            icon = R.drawable.lightbulb_on_10,
        )

        HsvColorPicker(
            modifier = Modifier.size(200.dp).padding(top = 15.dp),
            initialColor = Color(android.graphics.Color.parseColor("#" + lamp.color)),
            controller = controller,
            onColorChanged = { color ->
                if (color.fromUser)
                    lamp.changeColor(devicesViewModel, color.hexCode)
            }
        )
        Surface(
            modifier = Modifier
                .size(50.dp)
                .padding(top = 15.dp),
            shape = MaterialTheme.shapes.small,
            color = Color(android.graphics.Color.parseColor("#" + lamp.color))
        ) {}

    }
}
