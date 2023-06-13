package ar.edu.itba.homewizard.data.network.models

import com.google.gson.annotations.SerializedName
data class NetworkError(

    @SerializedName("code")
    val code: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("details")
    var details: List<String>? = null
)
