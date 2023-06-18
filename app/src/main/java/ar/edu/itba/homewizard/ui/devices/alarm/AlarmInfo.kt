package ar.edu.itba.homewizard.ui.devices.alarm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Alarm
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.PasswordInput
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun AlarmInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val alarm = devicesUiState.currentDevice as Alarm
    var changingCode by remember{ mutableStateOf(false) }
    val passwordInputModifier = Modifier.fillMaxWidth().padding(16.dp).height(64.dp)
    Column(
        modifier = Modifier.padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (changingCode) {
            var oldCode by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            var newCode by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            PasswordInput(label = "Código Anterior", text = oldCode, onValueChange = { oldCode = it }, modifier = passwordInputModifier)
            PasswordInput(label = "Código Nuevo", text = newCode, onValueChange = { newCode = it }, modifier = passwordInputModifier)
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        alarm.changeSecurityCode(devicesViewModel, oldCode.text, newCode.text)
                        changingCode = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                ) {
                    Text(text = "ACTUALIZAR", fontSize = 16.sp)
                }
                Button(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { changingCode = false },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                ) {
                    Text(text = "VOLVER", fontSize = 16.sp)
                }
            }
        } else {
            var code by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            var selected by remember { mutableStateOf(Alarm.statusValues.indexOf(alarm.status)) }
            val options = listOf(R.drawable.home_account, R.drawable.home_alert, R.drawable.alarm_light_off)

            PasswordInput(label = "Código", text = code, onValueChange = { code = it }, modifier = passwordInputModifier)
            CustomToggle(options, selected, modifier = Modifier.fillMaxWidth().padding(16.dp).height(64.dp)){
                alarm.setStatus(devicesViewModel, Alarm.statusValues[it], code.text) {
                    selected = it
                }
            }
            Button(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = { changingCode = true },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary, contentColor = MaterialTheme.colors.onPrimary)
            ) {
                Text("INGRESAR NUEVO CÓDIGO", fontSize = 16.sp )
            }
        }
    }
}
