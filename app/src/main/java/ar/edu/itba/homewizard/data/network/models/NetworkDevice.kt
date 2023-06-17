package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.data.models.devices.Door
import ar.edu.itba.homewizard.data.models.devices.Speaker
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor


data class NetworkDevice (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: NetworkDeviceType? = null,
    @SerializedName("state")
    var state: JsonObject? = null,
    @SerializedName("meta")
    var meta: Any? = null
){
    fun toDevice(): Device {
        println(this.name)
        val deviceType : DeviceType = this.type!!.toDeviceType()
        val deviceState : DeviceState = Gson().fromJson(this.state, deviceType.stateClass.java) as DeviceState

        // Edge case
        if (deviceType.deviceClass == Speaker::class)  {
            val speakerState = deviceState as Speaker.SpeakerState
            speakerState.song = Gson().fromJson(this.state!!.get("song"), Speaker.SpeakerSong::class.java)
        }

        val classInstance = deviceType.deviceClass.primaryConstructor?.call(
            this.id!!,
            this.name!!,
            deviceType,
            deviceState,
            this.meta!!
        )
        return classInstance as Device
    }
}

data class NetworkDeviceType (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("power_usage")
    var powerUsage: Int? = null
){
    fun toDeviceType() : DeviceType {
        return DeviceType.deviceTypes[this.id]!!
    }
}