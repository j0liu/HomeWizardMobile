package ar.edu.itba.homewizard.viewmodels

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.HomeWizardApplication
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(
    @ApplicationContext private val context : Context,
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
                        filteredDevices = it.filterDevices(devices = devices.value!!),
                        isLoading = false
                    )
                }
            }
        }

    }


    // TODO: Move names to constants
    val DEVICE_SP_KEY = "ar.edu.itba.homewizard.devices.notifications"
    fun setCurrentDevice(device: Device?) {
        var notificationsEnabled = false
        if (device != null) {
            notificationsEnabled = getApplication(context)
                .getSharedPreferences(DEVICE_SP_KEY, Context.MODE_PRIVATE)
                .getBoolean(device.id, false)
        }

        _uiState.update {
            it.copy(
                currentDevice = device,
                currentNotificationsEnabled = notificationsEnabled
            )
        }
    }

    fun toggleNotificationsForCurrentDevice() {
        getApplication(context)
            .getSharedPreferences(DEVICE_SP_KEY, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(uiState.value.currentDevice!!.id, !uiState.value.currentNotificationsEnabled)
            .apply()
        _uiState.update {
            it.copy(
                currentNotificationsEnabled = !uiState.value.currentNotificationsEnabled
            )
        }
    }

    fun setOverflowMenuVisibility(expanded: Boolean) {
        _uiState.update {
            it.copy(
                overflowExpanded = expanded
            )
        }
    }

    fun setFilterDialogOpen(isOpen: Boolean) {
        _uiState.update {
            it.copy(
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

    fun updateDevice (id : String,  onSuccess : suspend (Device) -> Unit = {}){
        viewModelScope.launch {
            runCatching {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                val res = deviceRepository.getDevice(id)
                onSuccess(res)
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
    
    fun filterByType(deviceType : DeviceType?) {
        _uiState.update {
            it.copy(
                filterType = deviceType,
                filteredDevices = it.filterDevices(filterType = deviceType)
            )
        }
    }

    fun setFilterType(name : String) {
        println(name)
        _uiState.update {
            it.copy(
                filterTypeName = name
            )
        }
    }
}