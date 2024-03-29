package ar.edu.itba.homewizard.ui.devices

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.ui.utils.SortingCriterias

data class DevicesUiState constructor(
    var devices: List<Device> = listOf(),
    val currentDevice: Device? = null,
    val currentNotificationsEnabled: Boolean = false,

    var filterTypeName : String = "",
    var filterType : DeviceType? = null,
    val filteredDevices : List<Device> = listOf(),

    val sortCriteriaName : String = SortingCriterias.BY_NAME,
    val sortCriteria : Comparator<Device> = Device.orderCriterias[sortCriteriaName]!!,

    // UI
    var isLoading: Boolean = true,
    var filterDialogIsOpen : Boolean = false,

    ) {
    fun filterDevices() : List<Device> {
        val filteredDevices = if (filterType != null) devices.filter {it.type == filterType} else devices
        return filteredDevices.sortedWith(sortCriteria)
    }
}