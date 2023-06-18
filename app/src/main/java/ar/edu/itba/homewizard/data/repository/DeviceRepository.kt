package ar.edu.itba.homewizard.data.repository

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.network.DeviceRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor (
    private val deviceRemoteDataSource: DeviceRemoteDataSource
) {
    suspend fun getDevices(): List<Device> {
        return deviceRemoteDataSource.getDevices().result.map { it.toDevice() }
    }

    suspend fun getDevice(deviceId : String): Device {
        return deviceRemoteDataSource.getDevice(deviceId).result.toDevice()
    }

    fun test() {
       println("Hm????")
    }

    suspend fun executeAction(deviceId: String, actionName: String, params: List<Any>) {
        deviceRemoteDataSource.executeAction(deviceId, actionName, params)
    }

}