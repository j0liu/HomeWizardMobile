package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DevicesScreen(devicesViewModel: DevicesViewModel = viewModel()) {
//    var devices = listOf(Device("1", "horno garage", DeviceType("1", "horno"), {}),Device("2", "aire patio", DeviceType("1", "ac"), {}))
    val devicesUiState by devicesViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Scaffold(
            topBar = {}
        ) {
            Column {
//                devices.forEach { device -> DeviceCard(device = device )
                  devicesUiState.devices.forEach { device : Device ->  DeviceCard(device = device)}
                }
            Button(
                onClick = { devicesViewModel.addDevice()},
            )
            {
                
            }
            }
        }
    }

