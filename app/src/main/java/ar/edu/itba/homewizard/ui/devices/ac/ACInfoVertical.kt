package ar.edu.itba.homewizard.ui.devices.ac

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.models.devices.AC
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.inputs.NumericController
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun ACInfoVertical(devicesViewModel: DevicesViewModel, ac: AC, options: List<Int>) {
    var selected by remember { mutableStateOf(AC.modeNames.indexOf(ac.mode)) }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        NumericController(value = ac.temperature, unit = "°", onValueChanged = {
            ac.setTemperature(devicesViewModel, it)
        })
        Row(
            modifier = Modifier
                .padding(4.dp),
        ) {
            PowerButton(
                selected = ac.status,
            ) {
                ac.toggle(devicesViewModel)
            }
        }

        CustomToggle(options = options, selected = selected, modifier = Modifier.padding(16.dp).height(64.dp), onSelectedChange = {
            selected = it
            ac.setMode(devicesViewModel, selected)
        })
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
        ) {
            DropdownButton(modifier = Modifier, "Velocidad ventilador", 20, AC.fanSpeedValues, ac.fanSpeed) {
                ac.setFanSpeed(devicesViewModel, it)
            }
            DropdownButton(modifier = Modifier, "Aspas verticales", 20, AC.verticalSwingValues, ac.verticalSwing) {
                ac.setVerticalSwing(devicesViewModel, it)
            }
            DropdownButton(modifier = Modifier, "Aspas horizontales", 20, AC.horizontalSwingValues, ac.horizontalSwing) {
                ac.setHorizontalSwing(devicesViewModel, it)
            }
        }
    }
}