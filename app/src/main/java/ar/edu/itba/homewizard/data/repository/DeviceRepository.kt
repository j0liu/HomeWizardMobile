package ar.edu.itba.homewizard.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.network.DeviceRemoteDataSource
import ar.edu.itba.homewizard.data.network.models.NetworkDeviceUpdate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor (
    private val deviceRemoteDataSource: DeviceRemoteDataSource
) {
    private var devicesCache: MutableLiveData<List<Device>> = MutableLiveData()
    suspend fun getDevices(): LiveData<List<Device>> {
        devicesCache.value = deviceRemoteDataSource.getDevices().result.map { it.toDevice() }
        return devicesCache
    }

    suspend fun updateDevices(): List<Device> {
        devicesCache.postValue(deviceRemoteDataSource.getDevices().result.map { it.toDevice() })
        return devicesCache.value!!
    }

    suspend fun getDevice(deviceId : String): Device {
        if (devicesCache.value != null) {
            val cachedDevice = devicesCache.value!!.find { it.id == deviceId }
            if (cachedDevice != null) {
                return cachedDevice
            }
        }
        return deviceRemoteDataSource.getDevice(deviceId).result.toDevice()
    }

    suspend fun updateDevice(deviceId : String) {
        val newDevice : Device = deviceRemoteDataSource.getDevice(deviceId).result.toDevice()
        val oldCache = (devicesCache.value as List<Device>).filter {
            it.id != deviceId
        }
        devicesCache.postValue(oldCache.plus(newDevice))
    }



    suspend fun <T> executeAction(deviceId: String, actionName: String, params: List<Any>) : T {
        return deviceRemoteDataSource.executeAction<T>(deviceId, actionName, params).result
    }

    suspend fun updateDevice(device: Device) {
        deviceRemoteDataSource.updateDevice(device.id, NetworkDeviceUpdate(device))
        updateDevice(device.id)
    }
}