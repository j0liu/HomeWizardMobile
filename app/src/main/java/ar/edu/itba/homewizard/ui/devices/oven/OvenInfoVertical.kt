package ar.edu.itba.homewizard.ui.devices.oven

import androidx.compose.foundation.layout.*
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
fun OvenInfoVertical(devicesViewModel: DevicesViewModel, oven : Oven, multiplier: Float = 1f){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Box(modifier = Modifier.padding(vertical = 15.dp*multiplier)){

                PowerButton(
                    selected = oven.status,
                    modifier = Modifier,
                    multiplier = multiplier
                ) {
                    oven.toggle(devicesViewModel)
                }
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
                icon = R.drawable.baseline_thermostat_24,
                multiplier = multiplier
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
        ) {
            DropdownButton(modifier = Modifier, "Heat", 24, Oven.heatModes, oven.heat, multiplier) {
                oven.setHeat(devicesViewModel, it)
            }
            Box(modifier = Modifier.padding(vertical = 20.dp*multiplier)){
                DropdownButton(modifier = Modifier, "Grill", 24, Oven.grillModes, oven.grill, multiplier) {
                    oven.setGrill(devicesViewModel, it)
                }
            }
            DropdownButton(modifier = Modifier, "Convection", 24, Oven.convectionModes, oven.convection, multiplier) {
                oven.setConvection(devicesViewModel, it)
            }
        }
    }
}