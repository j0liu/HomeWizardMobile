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
        testSet.add(Device("1", "horno garage", DeviceType.deviceTypes["oven"]!!, {}))
        testSet.add(Device("2", "aire", DeviceType.deviceTypes["ac"]!!, {}))
        testSet.add(Device("3", "persiana", DeviceType.deviceTypes["blinds"]!!, {}))
        testSet.add(Device("3", "heladera", DeviceType.deviceTypes["fridge"]!!, {}))
        testSet.add(Device("3", "luz", DeviceType.deviceTypes["lamp"]!!, {}))
        testSet.add(Device("3", "alarma", DeviceType.deviceTypes["alarm"]!!, {}))
        testSet.add(Device("3", "puerta", DeviceType.deviceTypes["door"]!!, {}))
        testSet.add(Device("3", "parlante", DeviceType.deviceTypes["speaker"]!!, {}))

        _uiState.value = DevicesUiState(devices = testSet)
    }

    fun setCurrentDevice(device : Device) {
        _uiState.value = DevicesUiState(devices = testSet, currentDevice = device)
    }
}