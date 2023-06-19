package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.*
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

class Alarm(
    id: String,
    name: String,
    type: DeviceType,
    state: DeviceState?,
    meta: MetaObject
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