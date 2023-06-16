package ar.edu.itba.homewizard

import android.app.Application
import android.content.Intent
import ar.edu.itba.homewizard.data.network.DeviceRemoteDataSource
import ar.edu.itba.homewizard.data.network.RetrofitClient
import ar.edu.itba.homewizard.data.network.RoutineRemoteDataSource
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.data.repository.RoutineRepository

class HomeWizardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val intent = Intent(this, EventService::class.java)
        startService(intent)
    }

    //private val deviceLocalDataSource: DeviceLocalDataSource
    //    get() = SportLocalDataSource()
    private val routineRemoteDataSource : RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiService())
    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.getApiService())
    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource)
    val routineRepository : RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)

}