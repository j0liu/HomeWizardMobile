package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import com.google.gson.annotations.SerializedName


data class NetworkDevice (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: NetworkDeviceType? = null,
    @SerializedName("state")
    var state: Any? = null,
    @SerializedName("meta")
    var meta: Any? = null
){
    //cast networkdevice to device
    fun toDevice(): Device {
        return Device(
            id = this.id!!,
            name = this.name!!,
            type = this.type!!.toDeviceType(),
            state = this.state,
            meta = this.meta!!
        )
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