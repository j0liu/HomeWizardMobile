package ar.edu.itba.homewizard.ui.devices

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.models.Device

data class DevicesUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    var devices: List<Device> = listOf(),
    val currentDevice: Device? = null,

    // UI
    var isLoading: Boolean = false,
    var overflowExpanded: Boolean = false
)