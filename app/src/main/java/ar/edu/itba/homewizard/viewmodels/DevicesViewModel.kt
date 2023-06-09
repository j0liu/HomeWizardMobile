package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.DeviceType
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DevicesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState : StateFlow<DevicesUiState> = _uiState.asStateFlow()

    var testSet : MutableSet<Device> = mutableSetOf()

    //Aca se arma la logica que pide a la api, este llamado actualiza Devices (esta en uiState)

    fun addDevice(){ //example
        testSet.add(Device("1", "horno garage", DeviceType("1", "horno"), {}))
        _uiState.value = DevicesUiState(devices = testSet)
    }

    fun setCurrentDevice(device : Device) {
        _uiState.value = DevicesUiState(devices = testSet, currentDevice = device)
    }
}