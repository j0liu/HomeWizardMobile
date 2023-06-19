package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.viewmodels.MainViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DevicesScreen(
    mainViewModel: MainViewModel,
    devicesViewModel: DevicesViewModel = hiltViewModel()
) {
    val mainUiState by mainViewModel.uiState.collectAsState()
    mainViewModel.setAfterCollapseBottomSheetAction {
        devicesViewModel.setCurrentDevice(null)
    }
    val scope = rememberCoroutineScope()
    mainViewModel.setBackHandler(scope)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        BottomSheetScaffold(
            sheetShape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
            scaffoldState = mainUiState.scaffoldState,
            sheetContent = {
                DeviceInfo(devicesViewModel = devicesViewModel, mainViewModel = mainViewModel)
            }
        ) {
            BoxWithConstraints {
                if(maxWidth < maxHeight) {
                    DevicesVertical(mainViewModel = mainViewModel, devicesViewModel = devicesViewModel)
                } else {
                    DevicesHorizontal(mainViewModel = mainViewModel, devicesViewModel = devicesViewModel)
                }
            }

        }
    }
}


