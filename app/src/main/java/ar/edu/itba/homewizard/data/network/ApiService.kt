package ar.edu.itba.homewizard.data.network

import ar.edu.itba.homewizard.data.network.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("/api/devices")
    suspend fun getDevices(): Response<NetworkResponse<List<NetworkDevice>>>

    //get device by id parameter
    @GET("/api/devices/{deviceId}")
    suspend fun getDevice(@Path("deviceId") deviceId: String): Response<NetworkResponse<NetworkDevice>>

    @PUT("/api/devices/{deviceId}/{actionName}")
    suspend fun <T> executeAction(@Path("deviceId") deviceId: String, @Path("actionName") actionName: String, @Body params: Array<Any>): Response<NetworkResponse<T>>

    @GET("/api/routines")
    suspend fun getRoutines(): Response<NetworkResponse<List<NetworkRoutine>>>

    @GET("/api/routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: String): Response<NetworkResponse<NetworkRoutine>>

    @PUT("/api/routines/{routineId}/execute")
    suspend fun executeRoutine(@Path("routineId") routineId: String): Response<NetworkResponse<Any>>
}