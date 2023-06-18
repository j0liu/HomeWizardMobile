package ar.edu.itba.homewizard.ui.devices.oven

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.AC
import ar.edu.itba.homewizard.data.models.devices.Oven
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.inputs.PowerButton
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun OvenInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val oven = devicesUiState.currentDevice as Oven

    BoxWithConstraints {
        if (maxWidth < maxHeight){
            OvenInfoVertical(devicesViewModel, oven)
        } else {
            OvenInfoHorizontal(devicesViewModel, oven)
        }
    }


}
