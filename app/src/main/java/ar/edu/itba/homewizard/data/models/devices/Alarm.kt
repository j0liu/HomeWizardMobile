package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

class Alarm(
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState?,
    override var meta: Any
) : Device(id, name, type, state, meta)
{

    companion object {
        private val actionNames = mapOf(
            "armedStay" to "armStay",
            "armedAway" to "armAway",
            "disarmed" to "disarm"
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

    fun setStatus(devicesViewModel : DevicesViewModel, status: String, securityCode : String, changeStatus: () -> Unit) {
        devicesViewModel.executeActionWithResult<Boolean>(Action(actionNames[status]!!, this, listOf(securityCode))) { result ->
            if (result) {
                this.status = status
                changeStatus()
            }
        }
    }

    fun changeSecurityCode(devicesViewModel : DevicesViewModel, oldSecurityCode : String, newSecurityCode : String) {
        devicesViewModel.executeActionWithResult<Boolean>(Action("changeSecurityCode", this, listOf(oldSecurityCode, newSecurityCode))) { result ->
            if (result)
                println("cambiada satisfactoriamente") //TODO: ver si poner snackbar o que para los errores
        }

    }

}