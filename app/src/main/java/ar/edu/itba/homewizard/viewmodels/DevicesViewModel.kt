package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.DeviceType
import ar.edu.itba.homewizard.data.network.RetrofitClient
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DevicesViewModel(private val deviceRepository : DeviceRepository ) : ViewModel() {
    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState : StateFlow<DevicesUiState> = _uiState.asStateFlow()

    var devices : MutableSet<Device> = mutableSetOf()
    init {
        viewModelScope.launch {
            runCatching {
                deviceRepository.getDevices().forEach { device ->
                    devices.add(device)
                }
                _uiState.update { it.copy(
                    devices = devices,
                    isLoading = false
                ) }
            }
        }
    }

    fun setCurrentDevice(device : Device) {
        _uiState.value = DevicesUiState(devices = devices, currentDevice = device)
    }
    private var fetchJob: Job? = null

}