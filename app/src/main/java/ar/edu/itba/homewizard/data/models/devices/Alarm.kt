package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

data class Alarm(
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device
{

    companion object {
        private val actionNames = mapOf(
            "armedStay" to "armStay",
            "armedAway" to "armAway",
            "disarmed" to "disarmed"
        )
        val statusValues : Array<String>
            get() = actionNames.keys.toTypedArray()
    }
    data class AlarmState (
        val status: String, // TODO: Enum?
    ) : DeviceState

    var status: String = ""
    init {
        this.status = (this.state as AlarmState).status
    }

    fun setStatus(devicesViewModel : DevicesViewModel, status: String, securityCode : String) {
        devicesViewModel.executeAction(Action(actionNames[status]!!, this, listOf(securityCode)))
        this.status = status
    }

    fun changeSecurityCode(devicesViewModel : DevicesViewModel, oldSecurityCode : String, newSecurityCode : String) {
        devicesViewModel.executeAction(Action("changeSecurityCode", this, listOf(oldSecurityCode, newSecurityCode)))
    }

}