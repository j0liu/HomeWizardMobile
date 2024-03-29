package ar.edu.itba.homewizard.data.network

import ar.edu.itba.homewizard.data.network.models.NetworkResponse
import ar.edu.itba.homewizard.data.network.models.NetworkRoutine
import ar.edu.itba.homewizard.data.network.models.NetworkRoutineUpdate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoutineRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource() {
    suspend fun getRoutines() : NetworkResponse<List<NetworkRoutine>> {
        return handleApiResponse {
            apiService.getRoutines()
        }
    }

    suspend fun getRoutine(routineId : String) : NetworkResponse<NetworkRoutine> {
        return handleApiResponse {
            apiService.getRoutine(routineId)
        }
    }

    suspend fun executeRoutine(routineId: String): NetworkResponse<Any> {
        return handleApiResponse {
            apiService.executeRoutine(routineId)
        }
    }

    suspend fun updateRoutine(routineId: String, body: NetworkRoutineUpdate): NetworkResponse<Boolean> {
        return handleApiResponse {
            apiService.updateRoutine(routineId, body)
        }
    }
}