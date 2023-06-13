package ar.edu.itba.homewizard.data.repository

import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.network.DeviceRemoteDataSource

class DeviceRepository (
    //private val deviceLocalDataSource: DeviceLocalDataSource
    private val deviceRemoteDataSource: DeviceRemoteDataSource
) {
    suspend fun getDevices(): List<Device> {
        return deviceRemoteDataSource.getDevices().result.map { it.toDevice() }
    }

    suspend fun getDevice(deviceId : String): Device {
        return deviceRemoteDataSource.getDevice(deviceId).result.toDevice()
    }

}