package ar.edu.itba.homewizard

import android.app.Application
import ar.edu.itba.homewizard.data.network.DeviceRemoteDataSource
import ar.edu.itba.homewizard.data.network.RetrofitClient
import ar.edu.itba.homewizard.data.repository.DeviceRepository

class HomeWizardApplication : Application() {

    //private val deviceLocalDataSource: DeviceLocalDataSource
    //    get() = SportLocalDataSource()

    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.getApiService())
    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource)

}