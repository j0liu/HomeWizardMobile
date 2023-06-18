package ar.edu.itba.homewizard.data.repository

import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.data.network.RoutineRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoutineRepository @Inject constructor (
    private val routineRemoteDataSource: RoutineRemoteDataSource
) {
    suspend fun getRoutines(): List<Routine> {
        return routineRemoteDataSource.getRoutines().result.map { it.toRoutine() }
    }

    suspend fun getRoutine(deviceId : String): Routine {
        return routineRemoteDataSource.getRoutine(deviceId).result.toRoutine()
    }

    suspend fun executeRoutine(routineId: String) : Any {
        return routineRemoteDataSource.executeRoutine(routineId).result
    }

}