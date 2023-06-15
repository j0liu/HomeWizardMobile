package ar.edu.itba.homewizard.viewmodels

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
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

@OptIn(ExperimentalMaterialApi::class)
class DevicesViewModel(private val deviceRepository : DeviceRepository ) : ViewModel() {
    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState : StateFlow<DevicesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _uiState.update { it.copy(
                    devices = deviceRepository.getDevices().toMutableSet(),
                    isLoading = false
                ) }
            }
        }
    }

    fun setCurrentDevice(device : Device) {
        _uiState.update {
            it.copy(
                devices = uiState.value.devices,
                currentDevice = device
            )
        }
    }

    fun setOverflowMenuVisibility(expanded: Boolean) {
        _uiState.update {
            it.copy(
                devices = uiState.value.devices,
                currentDevice = uiState.value.currentDevice,
                overflowExpanded = expanded
            )
        }
    }
    private var fetchJob: Job? = null

}