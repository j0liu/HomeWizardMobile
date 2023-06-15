package ar.edu.itba.homewizard.ui.devices.alarm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.PasswordInput
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.viewmodels.AlarmViewModel

@Composable
fun AlarmInfo(alarmViewModel: AlarmViewModel = viewModel()) {
    var changingCode = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (changingCode.value) {
            var oldCode by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            var newCode by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            PasswordInput(label = "Código Anterior", text = oldCode, onValueChange = { oldCode = it }, modifier = Modifier.padding(10.dp).height(64.dp))
            PasswordInput(label = "Código Nuevo", text = newCode, onValueChange = { newCode = it }, modifier = Modifier.padding(10.dp).height(64.dp))
            Button(
                modifier = Modifier.width(200.dp).height(64.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { changingCode.value = false },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary, contentColor = MaterialTheme.colors.surface)
            ) {
                Text(text = "Cambiar Código", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            var code by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(4, 4)))
            }
            var selected by remember { mutableStateOf(0) }
            val options = listOf(R.drawable.home_account, R.drawable.home_alert, R.drawable.alarm_light_off)

            PasswordInput(label = "Código", text = code, onValueChange = { code = it }, modifier = Modifier.padding(10.dp).height(64.dp))
            CustomToggle(options, selected, onSelectedChange = {  selected = it })
            Button(
                modifier = Modifier.width(200.dp).height(64.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { changingCode.value = true },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface, contentColor = MaterialTheme.colors.onPrimary)
            ) {
                Text("Cambiar Código", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
