package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
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
    var state: Any? = null,
    @SerializedName("meta")
    var meta: Any? = null
){
    //cast networkdevice to device
    fun toDevice(): Device {
        val deviceType : DeviceType = this.type!!.toDeviceType()
        val classInstance = deviceType.deviceClass.primaryConstructor?.call(
            this.id!!,
            this.name!!,
            deviceType,
            this.state,
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