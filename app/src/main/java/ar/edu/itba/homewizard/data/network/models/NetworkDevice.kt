package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.data.models.devices.Speaker
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
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
    var meta: NetworkMeta? = null
){
    fun toDevice(): Device {
        val deviceType : DeviceType = this.type!!.toDeviceType()
        val deviceState : DeviceState?
        val classInstance : Device

        if (this.state != null) {
            deviceState = Gson().fromJson(this.state, deviceType.stateClass.java) as DeviceState
            if (deviceType.deviceClass == Speaker::class)  { // Edge case
                val speakerState = deviceState as Speaker.SpeakerState
                if (this.state!!.has("song"))
                    speakerState.song = Gson().fromJson(this.state!!.get("song"), Speaker.SpeakerSong::class.java)
            }
            classInstance = deviceType.deviceClass.primaryConstructor?.call(
                this.id!!,
                this.name!!,
                deviceType,
                deviceState,
                this.meta!!.toMeta()
            ) as Device
        } else {
            classInstance = Device(this.id!!, this.name!!, deviceType, null, this.meta!!.toMeta())
        }

        return classInstance
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