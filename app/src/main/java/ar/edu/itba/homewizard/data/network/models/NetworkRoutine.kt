package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.Action
import ar.edu.itba.homewizard.data.Routine
import com.google.gson.annotations.SerializedName

data class NetworkRoutine(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("actions")
    var actions: List<NetworkAction>? = null,
    @SerializedName("meta")
    var meta: Any? = null
){
    fun toRoutine(): Routine {
        return Routine(
            id = this.id!!,
            name = this.name!!,
            actions = this.actions!!.map { it.toAction() },
            meta = this.meta!!
        )
    }
}

data class NetworkAction(
    @SerializedName("device")
    var device: NetworkDevice? = null,
    @SerializedName("actionName")
    var actionName: String? = null,
    @SerializedName("params")
    var params: List<Any>? = emptyList(),
){
    fun toAction() : Action {
        return Action(
            actionName = this.actionName!!,
            device = this.device!!.toDevice(),
            params = this.params!!
        )
    }
}