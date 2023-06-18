package ar.edu.itba.homewizard.ui.devices.blinds

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Blind
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel


@Composable

fun BlindInfoHorizontal(devicesViewModel: DevicesViewModel, blind : Blind) {
    Row {
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { blind.open(devicesViewModel) },
                modifier= Modifier.padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary),
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal),
                    contentDescription = "content description",
                )
            }
            Button(
                onClick = { blind.close(devicesViewModel) },
                modifier= Modifier.padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary),
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal_closed),
                    contentDescription = "content description", // TODO: Cambiar
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomSlider(
                value = blind.level.toFloat(),
                valueRange = 0f..100f,
                onValueChangeFinished = { blind.setLevel(devicesViewModel, it.toInt()) },
                title = "Nivel de la persiana",
                unit = "",
                icon = R.drawable.blinds,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            Column (
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
            ){
                // TODO: Traducir
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(45.dp).fillMaxWidth()
                ) {
                    Text(
                        text = "Nivel actual: ${blind.currentLevel}% (${blind.status})",
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                }
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.secondary,
                    progress = blind.currentLevel.toFloat() / 100f,
                )

            }

        }
    }
}