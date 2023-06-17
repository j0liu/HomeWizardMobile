package ar.edu.itba.homewizard.ui.devices.refrigerator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.CustomToggle

@Composable
fun RefrigeratorInfo() {
    // TODO: Mover a state
    var fridgeTemperature by remember { mutableStateOf(2f) }
    var freezerTemperature by remember { mutableStateOf(-8f) }

    Column (
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CustomSlider(
            value = fridgeTemperature,
            //onValueChange = { fridgeTemperature = it },
            valueRange = 2f..8f,
            onValueChangeFinished = { /*TODO*/ },
            title = "Temperatura heladera",
            unit = "°",
            icon = R.drawable.thermometer_low
        )
        CustomSlider(
            value = freezerTemperature,
            //onValueChange = { freezerTemperature = it },
            valueRange = -20f..-8f,
            onValueChangeFinished = { /*TODO*/ },
            title = "Temperatura freezer",
            unit = "°",
            icon = R.drawable.snowflake_thermometer
        )
        Row (
            modifier = Modifier
                .padding(8.dp)
        ){
            //Toggle button of three states
            var selected by remember { mutableStateOf(0) }
            val options = listOf(R.drawable.mdi_fridge, R.drawable.mdi_beach, R.drawable.mdi_party_popper)
            CustomToggle(options = options, selected = selected, onSelectedChange = {  selected = it })

        }
    }
}