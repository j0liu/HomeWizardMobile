package ar.edu.itba.homewizard.ui.devices.oven

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.devices.CustomSlider
import ar.edu.itba.homewizard.ui.devices.DropdownButton
import ar.edu.itba.homewizard.ui.theme.Surface
import ar.edu.itba.homewizard.viewmodels.ACViewModel
import ar.edu.itba.homewizard.viewmodels.OvenViewModel

@Composable
fun OvenInfo(ovenViewModel: OvenViewModel = viewModel()) {
    var ovenTemperature by remember { mutableStateOf(2f) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
        ) {
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(160.dp)
                    .background(Surface, shape = CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(80.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_power_settings_new_24),
                    contentDescription = "content description"
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp),
        ) {
            CustomSlider(
                value = ovenTemperature,
                onValueChange = { ovenTemperature = it },
                valueRange = 90f..230f,
                onValueChangeFinished = { /*TODO*/ },
                title = "Temperatura",
                unit = "°",
                icon = R.drawable.baseline_thermostat_24
            )
        }
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            DropdownButton(modifier = Modifier, "Calor", listOf("Convencional", "Abajo", "Arriba"))
            DropdownButton(modifier = Modifier, "Parrilla", listOf("Apagado", "Económico", "Completo"))
            DropdownButton(modifier = Modifier, "Convección", listOf("Apagado", "Económico", "Convencional"))
        }

    }

}