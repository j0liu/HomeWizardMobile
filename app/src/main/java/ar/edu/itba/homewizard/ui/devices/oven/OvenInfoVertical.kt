package ar.edu.itba.homewizard.ui.devices.oven

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Oven
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun OvenInfoVertical(devicesViewModel: DevicesViewModel, oven : Oven){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            PowerButton(
                selected = oven.status,
            ) {
                oven.toggle(devicesViewModel)
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp),
        ) {
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
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
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