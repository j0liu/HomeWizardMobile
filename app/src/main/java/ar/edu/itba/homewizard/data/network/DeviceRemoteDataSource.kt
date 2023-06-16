package ar.edu.itba.homewizard.data.network

import ar.edu.itba.homewizard.data.network.models.NetworkDevice
import ar.edu.itba.homewizard.data.network.models.NetworkResponse
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

    suspend fun executeAction(deviceId: String, actionName: String, params: List<Any>) : NetworkResponse<Any> {
        return handleApiResponse {
            apiService.executeAction(deviceId, actionName, params.toTypedArray())
        }
    }
}