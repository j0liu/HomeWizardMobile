package ar.edu.itba.homewizard.viewmodels

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class DevicesViewModel @Inject constructor(
    private val deviceRepository : DeviceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _uiState.update {
                    it.copy(
                        devices = deviceRepository.getDevices(),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun setCurrentDevice(device: Device) {
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

    fun executeAction(action: Action) {
        viewModelScope.launch {
            runCatching {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                deviceRepository.executeAction(action.device.id, action.actionName, action.params)
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}