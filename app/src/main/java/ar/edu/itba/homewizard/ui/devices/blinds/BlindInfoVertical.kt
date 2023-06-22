package ar.edu.itba.homewizard.ui.devices.blinds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Blind
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.utils.Translate
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun BlindInfoVertical(devicesViewModel: DevicesViewModel, blind : Blind, multiplier: Float = 1f) {
    var context = LocalContext.current
    Column (
        modifier = Modifier
            .padding(20.dp*multiplier)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { blind.open(devicesViewModel) },
                modifier= Modifier.padding(bottom = 10.dp*multiplier),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(20.dp*multiplier),
            ) {
                Icon(
                    modifier = Modifier.size(100.dp*multiplier),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal),
                    contentDescription = stringResource(R.string.open),
                )
            }
            Button(
                onClick = { blind.close(devicesViewModel) },
                modifier= Modifier.padding(bottom = 10.dp*multiplier),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(20.dp*multiplier),
            ) {
                Icon(
                    modifier = Modifier.size(100.dp*multiplier),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal_closed),
                    contentDescription = stringResource(R.string.close),
                )
            }
        }
        Box(
            modifier = Modifier.padding(vertical = 30.dp*multiplier)
        ){
            CustomSlider(
                value = blind.level.toFloat(),
                valueRange = 0f..100f,
                onValueChangeFinished = { blind.setLevel(devicesViewModel, it.toInt()) },
                title = stringResource(R.string.blind_level),
                unit = "",
                icon = R.drawable.blinds,
                multiplier = multiplier,
            )
        }

        Text(text = "${stringResource(R.string.current_level)}: ${blind.currentLevel}% (${Translate(context, blind.status)})", fontSize = 18.sp*multiplier , color = MaterialTheme.colors.onPrimary, modifier = Modifier.padding(bottom = 20.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp*multiplier),
            color = MaterialTheme.colors.secondary,
            progress = blind.currentLevel.toFloat() / 100f,
        )

    }

}