package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.inputs.CustomDialog
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
    val devicesUiState by devicesViewModel.uiState.collectAsState()
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
            topBar = {DevicesTopBar(devicesViewModel = devicesViewModel, devicesUiState = devicesUiState)},
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

@Composable
private fun DevicesTopBar( devicesViewModel: DevicesViewModel, devicesUiState : DevicesUiState) {
    TopAppBar (
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = stringResource(R.string.devices),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {},
        actions = {
            IconButton(onClick = {
                devicesViewModel.setFilterDialogOpen(true)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp, 30.dp),
                    imageVector =  ImageVector.vectorResource(R.drawable.filter_variant),
                    contentDescription = "Back"
                )
            }
            if (devicesUiState.currentDevice === null) {
                CustomDialog( openDialog = devicesUiState.filterDialogIsOpen, onClosureRequest = { devicesViewModel.setFilterDialogOpen(false) },
                    title = "${stringResource(R.string.filter)} ${stringResource(R.string.devices)}", submitText = stringResource(R.string.filter),
                    onSubmit = {

                        true
                    }
                ) {
                    Column() {

                    }
                }
            }

        }
    )
}