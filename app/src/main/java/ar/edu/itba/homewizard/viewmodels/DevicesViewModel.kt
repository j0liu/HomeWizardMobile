package ar.edu.itba.homewizard.viewmodels

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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
                val devices = deviceRepository.getDevices()
                devices.observeForever { dl ->
                    println("Observing...")
                    _uiState.update {
                        it.copy(
                            devices = dl,
                            currentDevice = dl.find { d -> d.id == it.currentDevice?.id },
                            isLoading = false
                        )
                    }
                }
                _uiState.update {
                    it.copy(
                        devices = devices.value!!,
                        isLoading = false
                    )
                }
            }
        }

    }

//    fun collapseBottomSheet(scope: CoroutineScope? = null) {
//        scope?.launch {
//            _uiState.value.scaffoldState.bottomSheetState.collapse()
//        }
//        _uiState.value.afterCollapseBottomSheet()
//        setCurrentDevice(null)
//    }

//    fun setAfterCollapseBottomSheetAction(function: () -> Unit) {
//        _uiState.update {
//            it.copy(
//                afterCollapseBottomSheet = function
//            )
//        }
//    }

    fun setCurrentDevice(device: Device?) {
        _uiState.update {
            it.copy(
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

    fun setFilterDialogOpen(isOpen: Boolean) {
        _uiState.update {
            it.copy(
                devices = uiState.value.devices,
                currentDevice = uiState.value.currentDevice,
                filterDialogIsOpen = isOpen
            )
        }
    }
    fun <T> executeActionWithResult(action: Action, onSuccess : suspend (T) -> Unit = {}) {
        viewModelScope.launch {
            runCatching {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                val response = deviceRepository.executeAction<T>(action.device.id, action.actionName, action.params)
                onSuccess(response)
                _uiState.update {
                    it.copy(isLoading = false)
                }

            }
        }
    }

    fun executeAction(action: Action) {
        return executeActionWithResult<Any>(action)
    }
}