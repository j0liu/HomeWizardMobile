package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeviceInfo(mainViewModel: MainViewModel, devicesViewModel: DevicesViewModel) {
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxHeight(0.99f),
        topBar = {
            TopAppBar (
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = devicesUiState.currentDevice?.name ?: "",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        mainViewModel.collapseBottomSheet(scope)
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp, 30.dp),
                            imageVector =  ImageVector.vectorResource(R.drawable.chevron_down),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        devicesViewModel.toggleNotificationsForCurrentDevice()
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp, 30.dp),
                            imageVector =  if (!devicesUiState.currentNotificationsEnabled) ImageVector.vectorResource(R.drawable.bell_cancel_outline) else ImageVector.vectorResource(R.drawable.bell_outline),
                            contentDescription = "Back"
                        )
                    }
                    IconButton(onClick = {
                        devicesViewModel.setOverflowMenuVisibility(true)
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp, 30.dp),
                            imageVector =  ImageVector.vectorResource(R.drawable.dots_horizontal),
                            contentDescription = "Back"
                        )
                    }
                    if (devicesUiState.currentDevice != null) {
                        DropdownMenu(
                            expanded = devicesUiState.overflowExpanded,
                            onDismissRequest = { devicesViewModel.setOverflowMenuVisibility(false) },
                        ) {
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Text("Edit")
                            }
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            )
        },
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            devicesUiState.currentDevice?.type?.infoScreen?.invoke()
        }
    }
}