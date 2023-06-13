package ar.edu.itba.homewizard.data.network

import ar.edu.itba.homewizard.data.network.models.NetworkDevice
import ar.edu.itba.homewizard.data.network.models.NetworkResponse

class DeviceRemoteDataSource (
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
}