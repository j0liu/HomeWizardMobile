package ar.edu.itba.homewizard.ui.devices.ac

import android.content.res.Resources
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.AC
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.inputs.NumericController
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable

fun ACInfoHorizontal(devicesViewModel: DevicesViewModel, ac: AC, options: List<Int>, multiplier: Float = 1f) {
    var selected by remember { mutableStateOf(AC.modeNames.indexOf(ac.mode)) }

    Row(
        modifier = Modifier
            .padding(top = 8.dp*multiplier)
    ){
        Column (

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
        ){
            NumericController(value = ac.temperature, unit = "°", fontSize = 60, multiplier = multiplier, onValueChanged = {
                ac.setTemperature(devicesViewModel, it)
            }, startLimit = 18, endLimit = 38)
            Box(modifier = Modifier.padding(vertical = 15.dp*multiplier)){
                PowerButton(
                    selected = ac.status,
                    modifier = Modifier,
                    multiplier = multiplier
                ) {
                    ac.toggle(devicesViewModel)
                }
            }
            CustomToggle(options = options, selected = selected, modifier = Modifier.padding(16.dp*multiplier).height(64.dp*multiplier), multiplier = multiplier, onSelectedChange = {
                selected = it
                ac.setMode(devicesViewModel, selected)
            })
        }
        Column (
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
        ){
            DropdownButton(modifier = Modifier, String.format(stringResource(R.string.fan_speed), "\n"), 18, AC.fanSpeedValues, ac.fanSpeed, multiplier) {
                ac.setFanSpeed(devicesViewModel, it)
            }
            Box(modifier = Modifier.padding(vertical = 20.dp*multiplier)){
                DropdownButton(modifier = Modifier, String.format(stringResource(R.string.vertical_blades), "\n"), 18, AC.verticalSwingValues, ac.verticalSwing, multiplier) {
                    ac.setVerticalSwing(devicesViewModel, it)
                }
            }
            DropdownButton(modifier = Modifier, String.format(stringResource(R.string.horizontal_blades), "\n"), 18, AC.horizontalSwingValues, ac.horizontalSwing, multiplier) {
                ac.setHorizontalSwing(devicesViewModel, it)
            }
        }
    }
}