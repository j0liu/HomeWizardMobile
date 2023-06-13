package ar.edu.itba.homewizard.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkDeviceList (
    @SerializedName("result")
    var result: List<NetworkDevice> = listOf(),
)