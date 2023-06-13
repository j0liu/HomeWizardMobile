package ar.edu.itba.homewizard.ui.devices

import ar.edu.itba.homewizard.data.Device

data class DevicesUiState(
    val devices: MutableSet<Device> = mutableSetOf(),
    var currentDevice: Device? = null,
    val isLoading: Boolean = false
)