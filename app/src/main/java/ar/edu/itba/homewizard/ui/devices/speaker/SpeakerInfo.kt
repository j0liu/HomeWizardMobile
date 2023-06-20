package ar.edu.itba.homewizard.ui.devices.speaker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Speaker
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfoHorizontal
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfoVertical
import ar.edu.itba.homewizard.ui.inputs.CustomDropdownMenu
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.SpeakerViewModel
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.delay


@Composable
fun SpeakerInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
//    val devicesUiState by speakerViewModel.uiState.collectAsState()
    // list of genres
    val scope  = rememberCoroutineScope()
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val speaker = devicesUiState.currentDevice as Speaker

    BoxWithConstraints {
        if (maxWidth < maxHeight)
            SpeakerInfoVertical(devicesViewModel, speaker, if (maxHeight > ScreenSize.tabletHeight) 1.8f else 1f)
        else
            SpeakerInfoHorizontal(devicesViewModel, speaker, if (maxWidth > ScreenSize.tabletWidth) 1.8f else 1f)
    }

}