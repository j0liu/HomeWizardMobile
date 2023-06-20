package ar.edu.itba.homewizard.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.bridges.SnackbarBridge
import ar.edu.itba.homewizard.bridges.SnackbarType
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(
    @ApplicationContext private val context : Context,
    private val bridge: SnackbarBridge,
    private val deviceRepository : DeviceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _uiState.update { it.copy(isLoading = true) }
                val devices = deviceRepository.getDevices()
                devices.observeForever { dl ->
                    _uiState.update {
                        it.copy(
                            devices = dl,
                            currentDevice = dl.find { d -> d.id == it.currentDevice?.id },
                        )
                    }
                    _uiState.update {
                        it.copy(
                            filteredDevices = it.filterDevices(),
                            isLoading = false
                        )
                    }
                }
                _uiState.update {
                    it.copy(devices = devices.value!!)
                }
                _uiState.update {
                    it.copy(
                        filteredDevices = it.filterDevices(),
                        isLoading = false
                    )
                }
            }
        }

    }


    val DEVICE_SP_KEY = "ar.edu.itba.homewizard.devices.notifications"
    fun setCurrentDevice(device: Device?) {
        _uiState.update { it.copy(isLoading = true) }
        var notificationsEnabled = false
        if (device != null) {
            notificationsEnabled = getApplication(context)
                .getSharedPreferences(DEVICE_SP_KEY, Context.MODE_PRIVATE)
                .getBoolean(device.id, false)
        }

        viewModelScope.launch {
            runCatching {
                _uiState.update {
                    it.copy(
                        currentDevice = if (device == null) null else deviceRepository.getDevice(deviceId = device!!.id),
                        currentNotificationsEnabled = notificationsEnabled,
                        isLoading = false
                    )
                }
            }
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
                action.device.qtyUses++
                deviceRepository.updateDevice(action.device)

                _uiState.update {
                    it.copy(isLoading = false)
                }

            }
        }
    }

    fun executeAction(action: Action) {
        return executeActionWithResult<Any>(action)
    }

    fun updateDevice(id : String, scope: CoroutineScope){
        scope.launch {
            deviceRepository.updateDevice(id)
        }
    }
    
    fun filterByType(deviceType : DeviceType?) {
        _uiState.update {
            it.copy(
                filterType = deviceType,
                sortCriteria = Device.orderCriterias[it.sortCriteriaName]!!
            )
        }
        _uiState.update {
            it.copy(filteredDevices = it.filterDevices())
        }
    }

    fun setFilterType(name : String) {
        _uiState.update {
            it.copy(
                filterTypeName = name
            )
        }
    }

    fun setOrderCriteria(name : String) {
        _uiState.update {
            it.copy(
                sortCriteriaName = name
            )
        }
    }

    fun putSnackbar(message: Int, type: SnackbarType = SnackbarType.INFO) {
        bridge.sendMessage(context.getString(message), type)
    }
}