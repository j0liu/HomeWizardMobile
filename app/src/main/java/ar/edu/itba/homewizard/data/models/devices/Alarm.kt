package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType

data class Alarm(
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device
{
    data class AlarmState (
        val status: String, // TODO: Enum?
    ) : DeviceState
}