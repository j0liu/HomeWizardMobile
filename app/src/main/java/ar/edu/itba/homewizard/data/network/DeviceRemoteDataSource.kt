package ar.edu.itba.homewizard.data.network

import ar.edu.itba.homewizard.data.network.models.NetworkDevice
import ar.edu.itba.homewizard.data.network.models.NetworkDeviceUpdate
import ar.edu.itba.homewizard.data.network.models.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource() {
    suspend fun getDevices() : NetworkResponse<List<NetworkDevice>> {
        return handleApiResponse {
            apiService.getDevices()
        }
    }
    suspend fun getDevice(deviceId: String) : NetworkResponse<NetworkDevice> {
        return handleApiResponse {
            apiService.getDevice(deviceId)
        }
    }

    suspend fun <T> executeAction(deviceId: String, actionName: String, params: List<Any>) : NetworkResponse<T> {
        return handleApiResponse<NetworkResponse<T>> {
            apiService.executeAction<T>(deviceId, actionName, params.toTypedArray())
        }
    }

    suspend fun updateDevice(@Path("deviceId") deviceId: String, @Body body: NetworkDeviceUpdate): NetworkResponse<Boolean> {
        return handleApiResponse {
            apiService.updateDevice(deviceId, body)
        }
    }
}