package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType

data class Blind (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device {
    data class BlindState (
        val status: String,
        val level: Int,
        val currentLevel: Int
    ) : DeviceState
}