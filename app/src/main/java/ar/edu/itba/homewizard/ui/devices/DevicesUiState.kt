package ar.edu.itba.homewizard.ui.devices

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

data class DevicesUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    var devices: List<Device> = listOf(),
    var filterDevices : Flow<Device> = devices.asFlow(),
    val currentDevice: Device? = null,

    // UI
    var isLoading: Boolean = false,
    var overflowExpanded: Boolean = false,
    var filterDialogIsOpen : Boolean = false,
    var filterType : DeviceType? = null,

)