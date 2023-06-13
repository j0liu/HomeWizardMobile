package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.DeviceType
import ar.edu.itba.homewizard.data.network.RetrofitClient
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DevicesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState : StateFlow<DevicesUiState> = _uiState.asStateFlow()

    var testSet : MutableSet<Device> = mutableSetOf()

    //Aca se arma la logica que pide a la api, este llamado actualiza Devices (esta en uiState)

    fun addDevice(){ //example
        fetchDevices()
//        testSet.add(Device("1", "horno garage", DeviceType.deviceTypes["oven"]!!, {}))
//        testSet.add(Device("2", "aire", DeviceType.deviceTypes["ac"]!!, {}))
//        testSet.add(Device("3", "persiana", DeviceType.deviceTypes["blinds"]!!, {}))
//        testSet.add(Device("3", "heladera", DeviceType.deviceTypes["fridge"]!!, {}))
//        testSet.add(Device("3", "luz", DeviceType.deviceTypes["lamp"]!!, {}))
//        testSet.add(Device("3", "alarma", DeviceType.deviceTypes["alarm"]!!, {}))
//        testSet.add(Device("3", "puerta", DeviceType.deviceTypes["door"]!!, {}))
//        testSet.add(Device("3", "parlante", DeviceType.deviceTypes["speaker"]!!, {}))

        _uiState.value = DevicesUiState(devices = testSet)
    }

    fun setCurrentDevice(device : Device) {
        _uiState.value = DevicesUiState(devices = testSet, currentDevice = device)
    }

    private var fetchJob: Job? = null

    fun fetchDevices() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getDevices()
            }.onSuccess { response ->
                val set = mutableSetOf<Device>()
//                print(response.body()?.result)
                response.body()?.result?.forEach { device ->
                    set.add(Device(device.id!!, device.name!!, device.state, DeviceType.deviceTypes[device.type!!.id]!!, {}))
                }
                _uiState.update { it.copy(
                    devices = set,
                    isLoading = false
                ) }
            }.onFailure { e ->
                _uiState.update { it.copy(
                    //message = e.message,
                    isLoading = false
                ) }
            }
        }
    }
}