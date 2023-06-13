package ar.edu.itba.homewizard.data.network

import ar.edu.itba.homewizard.data.network.models.NetworkDeviceList
import ar.edu.itba.homewizard.data.network.models.NetworkRoutine
import ar.edu.itba.homewizard.data.network.models.NetworkRoutineList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/devices")
    suspend fun getDevices(): Response<NetworkDeviceList>

    @GET("/api/routines")
    suspend fun getRoutines(): Response<NetworkRoutineList>
}