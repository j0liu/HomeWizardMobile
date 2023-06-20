package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Routine
import com.google.gson.annotations.SerializedName

class NetworkRoutineUpdate(routine : Routine) {
    @SerializedName("name")
    val name: String = routine.name

    @SerializedName("meta")
    val meta: NetworkMeta = NetworkMeta(routine.qtyUses)

    @SerializedName("actions")
    val actions: List<NetworkActionUpdate> = routine.actions.map { NetworkActionUpdate(it) }
}

class NetworkActionUpdate(action : Action) {
    @SerializedName("device")
    val device: NetworkDevicePartial = NetworkDevicePartial(action.device.id)
    @SerializedName("actionName")
    val actionName: String = action.actionName
    @SerializedName("params")
    val params: List<Any> = action.params
}

data class NetworkDevicePartial(
    @SerializedName("id")
    val id: String
)