package ar.edu.itba.homewizard.ui.devices.refrigerator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Fridge
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun RefrigeratorMultiplier(devicesViewModel: DevicesViewModel, fridge: Fridge, multiplier: Float){

    Column (
        modifier = Modifier.fillMaxHeight()
            .padding(horizontal = 10.dp*multiplier),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 25.dp*multiplier)
        ){
            CustomSlider(
                value = fridge.temperature.toFloat(),
                valueRange = 2f..8f,
                onValueChangeFinished = {fridge.changeTemperature(devicesViewModel, it.toInt())},
                title = stringResource(R.string.fridge_temperature),
                unit = "°",
                icon = R.drawable.thermometer_low,
                multiplier = multiplier
            )
        }
        Box(
            modifier = Modifier.padding(bottom = 20.dp*multiplier)
        ){
            CustomSlider(
                value = fridge.freezerTemperature.toFloat(),
                valueRange = -20f..-8f,
                onValueChangeFinished = { fridge.changeFreezerTemperature(devicesViewModel, it.toInt()) },
                title = stringResource(R.string.freezer_temperature),
                unit = "°",
                icon = R.drawable.snowflake_thermometer,
                multiplier = multiplier
            )
        }
        Row (
            modifier = Modifier
        ){
            //Toggle button of three states
            val options = listOf(R.drawable.mdi_fridge, R.drawable.mdi_beach, R.drawable.mdi_party_popper)
            CustomToggle(options = options, selected = fridge.getMode(), multiplier = multiplier, modifier = Modifier.padding(8.dp*multiplier).height(64.dp*multiplier), onSelectedChange = { selection -> fridge.setMode(devicesViewModel, selection)  })
        }
    }
}