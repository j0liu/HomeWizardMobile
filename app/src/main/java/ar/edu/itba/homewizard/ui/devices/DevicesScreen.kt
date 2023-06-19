package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.ui.constants.ScreenSize
import ar.edu.itba.homewizard.ui.inputs.CustomDialog
import ar.edu.itba.homewizard.ui.inputs.CustomDropdownMenu
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import kotlinx.coroutines.launch


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
                LazyVerticalGrid (
                    columns = GridCells.Fixed(
                        if(maxWidth < maxHeight) {
                            if(maxHeight > ScreenSize.tabletHeight) 2 else 1
                        }
                        else {
                            if(maxWidth > ScreenSize.tabletWidth) 3 else 2
                        }
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, bottom = 56.dp)
                ) {
                    items(devicesUiState.filteredDevices, key = { device: Device -> device.id }) { device : Device ->
                        DeviceCard(
                            device = device,
                            onClick = { deviceSelected ->
                                devicesViewModel.setCurrentDevice(deviceSelected)
                                mainViewModel.setBottomBarVisibility(false)
                                scope.launch {
                                    mainUiState.scaffoldState.bottomSheetState.expand()
                                }
                            }
                        )
                    }

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
                        devicesViewModel.filterByType(DeviceType.deviceTypesByName[devicesUiState.filterTypeName])
                        true
                    }
                ) {
                    Column(
                        modifier = Modifier.background(color = MaterialTheme.colors.primary)
                    ) {
                        //TODO: change dropdown to toggle group
                        CustomDropdownMenu(
                            modifier = Modifier.padding(bottom = 16.dp).background(color = MaterialTheme.colors.surface, shape = RectangleShape),
                            elements = listOf(listOf("all"), DeviceType.deviceTypesByName.keys).flatten(),
                            title = stringResource(R.string.type),
                            selected = devicesUiState.filterTypeName
                        ){devicesViewModel.setFilterType(it)}

                        CustomDropdownMenu(
                            modifier = Modifier.background(color = MaterialTheme.colors.surface, shape = RectangleShape),
                            elements = Device.orderCriteriaNames,
                            title = stringResource(R.string.orderby),
                            selected = devicesUiState.orderCriteriaName
                        ){devicesViewModel.setOrderCriteria(it)}
                    }
                }
            }

        }
    )
}