package ar.edu.itba.homewizard.ui.devices.ac

import androidx.compose.foundation.border
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

fun ACInfoHorizontal(devicesViewModel: DevicesViewModel, ac: AC, options: List<Int>) {
    var selected by remember { mutableStateOf(AC.modeNames.indexOf(ac.mode)) }

    Row(
        modifier = Modifier
            .padding(top = 8.dp)
    ){
        Column (
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
        ){
            NumericController(value = ac.temperature, unit = "°", fontSize = 60, onValueChanged = {
                ac.setTemperature(devicesViewModel, it)
            })
            PowerButton(
                Modifier.size(80.dp).padding(vertical = 2.dp),
                selected = ac.status,
            ) {
                ac.toggle(devicesViewModel)
            }
            CustomToggle(options = options, selected = selected, modifier = Modifier.padding(16.dp).height(64.dp), onSelectedChange = {
                selected = it
                ac.setMode(devicesViewModel, selected)
            })
        }
        Column (
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
//            verticalArrangement = Arrangement.Center
        ){
            DropdownButton(modifier = Modifier, "Velocidad\nventilador", 20, AC.fanSpeedValues, ac.fanSpeed) {
                ac.setFanSpeed(devicesViewModel, it)
            }
            DropdownButton(modifier = Modifier, "Aspas\nverticales", 20, AC.verticalSwingValues, ac.verticalSwing) {
                ac.setVerticalSwing(devicesViewModel, it)
            }
            DropdownButton(modifier = Modifier, "Aspas\nhorizontales", 20, AC.horizontalSwingValues, ac.horizontalSwing) {
                ac.setHorizontalSwing(devicesViewModel, it)
            }
        }
    }
}