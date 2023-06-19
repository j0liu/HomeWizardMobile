package ar.edu.itba.homewizard.ui.devices.ac

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.AC
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfoHorizontal
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfoVertical
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.inputs.NumericController
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ACInfo(
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val ac = devicesUiState.currentDevice as AC
    val options = listOf(R.drawable.white_balance_sunny, R.drawable.snowflake, R.drawable.weather_windy)

    BoxWithConstraints {
        if (maxWidth < maxHeight)
            ACInfoVertical(devicesViewModel, ac, options, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            ACInfoHorizontal(devicesViewModel, ac, options, if (maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)
    }

}



