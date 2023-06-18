package ar.edu.itba.homewizard.ui.devices.door

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import ar.edu.itba.homewizard.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.data.models.devices.Door
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.DoorViewModel

@Composable
fun DoorInfo (devicesViewModel: DevicesViewModel = hiltViewModel()){

    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val door = devicesUiState.currentDevice as Door

    BoxWithConstraints {
        if (maxWidth < maxHeight) {
            DoorInfoVertical(devicesViewModel, door)
        } else {
            DoorInfoHorizontal(devicesViewModel, door)
        }
    }


}