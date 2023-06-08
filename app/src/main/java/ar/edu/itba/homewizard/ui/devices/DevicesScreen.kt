package ar.edu.itba.homewizard.ui.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.DeviceType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DevicesScreen() {
    var devices = listOf(Device("1", "horno garage", DeviceType("1", "horno"), {}),Device("2", "aire patio", DeviceType("1", "ac"), {}))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Scaffold(
            topBar = {}
        ) {
            Column {
                devices.forEach { device -> DeviceCard(device = device )
                }
            }
        }
    }
}