package ar.edu.itba.homewizard.data.network.models

import ar.edu.itba.homewizard.data.models.MetaObject
import com.google.gson.annotations.SerializedName

data class NetworkMeta (
    @SerializedName("qtyUses")
    val qtyUses : Int?
) {
    fun toMeta() : MetaObject{
        return MetaObject(this.qtyUses?:0)
    }
}