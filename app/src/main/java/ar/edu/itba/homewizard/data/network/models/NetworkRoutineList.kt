package ar.edu.itba.homewizard.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkRoutineList (
    @SerializedName("result")
    var result: List<NetworkRoutine> = listOf(),
)