package ar.edu.itba.homewizard.ui.devices.alarm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Alarm
import ar.edu.itba.homewizard.data.models.devices.Fridge
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.PasswordInput
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

// TODO: Pasar mensajes a strings
@Composable
fun AlarmMultiplier(devicesViewModel: DevicesViewModel, alarm: Alarm, multiplier: Float){
    var changingCode by remember{ mutableStateOf(false) }
    val passwordInputModifier = Modifier.fillMaxWidth().padding(bottom=16.dp*multiplier).height(80.dp*multiplier).padding(top = 16.dp*multiplier)
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(start = 30.dp * multiplier, end = 30.dp*multiplier),
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
            PasswordInput(
                label = "Código Anterior",
                text = oldCode,
                onValueChange = { oldCode = it },
                modifier = passwordInputModifier,
                multiplier=multiplier
            )
            PasswordInput(
                label = "Código Nuevo",
                text = newCode,
                onValueChange = { newCode = it },
                modifier = passwordInputModifier,
                multiplier=multiplier
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
            ) {
                Button(
                    modifier = Modifier.padding(16.dp*multiplier),
                    onClick = {
                        focusManager.clearFocus()
                        alarm.changeSecurityCode(devicesViewModel, oldCode.text, newCode.text) {
                            changingCode = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    enabled = wrongPasswordMessage(oldCode.text).isEmpty() &&
                            wrongPasswordMessage(newCode.text).isEmpty() &&
                            oldCode.text.length == 4 && newCode.text.length == 4
                ) {
                    Text(text = "ACTUALIZAR", fontSize = 16.sp*multiplier)
                }
                Button(
                    modifier = Modifier.padding(16.dp*multiplier),
//                    shape = RoundedCornerShape(10.dp*multiplier),
                    onClick = { changingCode = false },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                ) {
                    Text(text = "VOLVER", fontSize = 16.sp*multiplier)
                }
            }
        } else {
            var code by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            var selected by remember { mutableStateOf(Alarm.statusValues.indexOf(alarm.status)) }
            val options = listOf(R.drawable.home_account, R.drawable.home_alert, R.drawable.alarm_light_off)

            PasswordInput(label = "Código", text = code, onValueChange = { code = it }, modifier = passwordInputModifier, multiplier)
            CustomToggle(options, selected, modifier = Modifier.fillMaxWidth().padding(16.dp*multiplier).height(64.dp*multiplier), multiplier){
                alarm.setStatus(devicesViewModel, Alarm.statusValues[it], code.text) {
                    selected = it
                }
            }
            Button(
                modifier = Modifier.padding(16.dp*multiplier),
                onClick = { changingCode = true },
                enabled = wrongPasswordMessage(code.text).isEmpty() || code.text.isEmpty(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary, contentColor = MaterialTheme.colors.onPrimary)
            ) {
                Text("INGRESAR NUEVO CÓDIGO", fontSize = 16.sp*multiplier)
            }
        }
    }
}
fun wrongPasswordMessage(password: String): String {
    if (password.length != 4 && password.isNotEmpty()) return "El código debe tener 4 dígitos"
    if (!password.all { char -> char.isDigit() }) return "El código debe ser numérico"
    return ""
}