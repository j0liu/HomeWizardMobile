package ar.edu.itba.homewizard.ui.devices.refrigerator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Fridge
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun RefrigeratorInfo (devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val fridge = devicesUiState.currentDevice as Fridge

    Column (
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CustomSlider(
            value = fridge.temperature.toFloat(),
            valueRange = 2f..8f,
            onValueChangeFinished = {fridge.changeTemperature(devicesViewModel, it.toInt())},
            title = "Temperatura heladera",
            unit = "°",
            icon = R.drawable.thermometer_low
        )
        CustomSlider(
            value = fridge.freezerTemperature.toFloat(),
            valueRange = -20f..-8f,
            onValueChangeFinished = { fridge.changeFreezerTemperature(devicesViewModel, it.toInt()) },
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
            CustomToggle(options = options, selected = fridge.getMode(), onSelectedChange = { selection -> fridge.setMode(devicesViewModel, selection)  })

        }
    }
}