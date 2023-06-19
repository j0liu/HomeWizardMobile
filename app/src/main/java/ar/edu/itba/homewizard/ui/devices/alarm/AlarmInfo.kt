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
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.devices.refrigerator.RefrigeratorMultiplier
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.PasswordInput
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@Composable
fun AlarmInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val alarm = devicesUiState.currentDevice as Alarm
    BoxWithConstraints {
        if(maxWidth < maxHeight)
            AlarmMultiplier(devicesViewModel, alarm, if (maxHeight > ScreenSize.tabletHeight) 1.5f else 1f)
        else
            AlarmMultiplier(devicesViewModel, alarm, if (maxWidth > ScreenSize.tabletWidth) 1.5f else 1f)
    }

}
