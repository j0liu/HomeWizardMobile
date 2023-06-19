package ar.edu.itba.homewizard.ui.devices.lamp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Lamp
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker

@Composable
fun LampInfoHorizontal(devicesViewModel: DevicesViewModel, lamp: Lamp, controller: ColorPickerController) {
    Row {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PowerButton(
                selected = lamp.status,
                modifier = Modifier
                    .size(150.dp)
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
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    .size(80.dp)
                    .padding(top = 15.dp),
                shape = MaterialTheme.shapes.small,
                color = Color(android.graphics.Color.parseColor("#" + lamp.color))
            ) {}
        }
    }
}
