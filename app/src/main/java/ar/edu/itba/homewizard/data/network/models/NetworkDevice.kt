package ar.edu.itba.homewizard.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkDevice (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: NetworkDeviceType? = null,
    @SerializedName("meta")
    var meta: Any? = null
)

data class NetworkDeviceType (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("power_usage")
    var powerUsage: Int? = null
)