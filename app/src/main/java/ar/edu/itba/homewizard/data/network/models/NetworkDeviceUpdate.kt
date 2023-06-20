package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.Device
import com.google.gson.annotations.SerializedName

class NetworkDeviceUpdate (
    device : Device
) {
    @SerializedName("id")
    val id: String

    @SerializedName("name")
    val name: String

    @SerializedName("meta")
    val meta: NetworkMeta
    init {
        this.id = device.id
        this.name = device.name
        this.meta = NetworkMeta(device.qtyUses)
    }
}