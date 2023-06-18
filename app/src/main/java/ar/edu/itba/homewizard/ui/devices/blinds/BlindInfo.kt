package ar.edu.itba.homewizard.ui.devices.blinds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.Secondary
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Blind
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun BlindInfo(
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val blind = devicesUiState.currentDevice as Blind

    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { blind.open(devicesViewModel) },
                modifier= Modifier.padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary),
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(
                    modifier = Modifier.size(100.dp),
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
                    modifier = Modifier.size(100.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal_closed),
                    contentDescription = "content description", // TODO: Cambiar
                )
            }
        }
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
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            progress = blind.currentLevel.toFloat()
        )
//            Spacer(modifier = Modifier.width(40.dp))
    }
}