package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType

data class AC (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device {
        data class ACState (
            val status: String,
            val temperature: Int,
            val mode: String,
            val verticalSwing: String,
            val horizontalSwing: String,
            val fanSpeed: String
        ) : DeviceState
}