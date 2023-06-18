package ar.edu.itba.homewizard.ui.devices.oven

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Oven
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun OvenInfoHorizontal(devicesViewModel: DevicesViewModel, oven : Oven) {
    Row(
        modifier = Modifier.padding(bottom = 50.dp).fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PowerButton(
                selected = oven.status,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                oven.toggle(devicesViewModel)
            }
            CustomSlider(
                value = oven.temperature.toFloat(),
//            onValueChange = { lamp.brightness = it.toInt() },
                valueRange = 90f..230f,
                onValueChangeFinished = { oven.setTemperature(devicesViewModel, it.toInt()) },
                title = "Temperatura",
                unit = "°",
                icon = R.drawable.baseline_thermostat_24
            )
        }
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            DropdownButton(modifier = Modifier, "Heat", 24, Oven.heatModes, oven.heat) {
                oven.setHeat(devicesViewModel, it)
            }
            DropdownButton(modifier = Modifier, "Grill", 24, Oven.grillModes, oven.grill) {
                oven.setGrill(devicesViewModel, it)
            }
            DropdownButton(modifier = Modifier, "Convection", 24, Oven.convectionModes, oven.convection) {
                oven.setConvection(devicesViewModel, it)
            }
        }
    }
}