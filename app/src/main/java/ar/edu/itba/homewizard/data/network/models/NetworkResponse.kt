package ar.edu.itba.homewizard.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkResponse<T> (
    @SerializedName("result")
    var result: T,
)