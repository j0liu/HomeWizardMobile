package ar.edu.itba.homewizard.data.repository

import ar.edu.itba.homewizard.data.Routine
import ar.edu.itba.homewizard.data.network.RoutineRemoteDataSource

class RoutineRepository (
    private val routineRemoteDataSource: RoutineRemoteDataSource
) {
    suspend fun getRoutines(): List<Routine> {
        return routineRemoteDataSource.getRoutines().result.map { it.toRoutine() }
    }

    suspend fun getRoutine(deviceId : String): Routine {
        return routineRemoteDataSource.getRoutine(deviceId).result.toRoutine()
    }

}