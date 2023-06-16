package ar.edu.itba.homewizard

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class EventData(
    @SerializedName("timestamp") var timestamp: String,
    @SerializedName("deviceId") var deviceId: String,
    @SerializedName("event") var event: String,
    @SerializedName("args") var args : Map<String, String> = mapOf()
)