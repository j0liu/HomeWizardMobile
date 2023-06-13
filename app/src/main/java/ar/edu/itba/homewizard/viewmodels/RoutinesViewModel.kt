package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.Action
import ar.edu.itba.homewizard.data.Routine
import ar.edu.itba.homewizard.data.network.RetrofitClient
import ar.edu.itba.homewizard.ui.routines.RoutinesUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutinesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState : StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    var testSet : MutableSet<Routine> = mutableSetOf()

    //Aca se arma la logica que pide a la api, este llamado actualiza Devices (esta en uiState)

    fun addRoutine(){ //example
//        testSet.add(Routine("1", "a dormir", arrayOf(
//            Action("1", Device("3", "luz",DeviceType.deviceTypes["lamp"]!!, {}), emptyArray(), "a"),
//            Action("1", Device("3", "luz", DeviceType.deviceTypes["oven"]!!, {}), emptyArray(), "a")
//        ), "a"))
        fetchRoutines()
        _uiState.value = RoutinesUiState(routines = testSet)
    }

    fun setCurrentRoutine(routine : Routine) {
        _uiState.value = RoutinesUiState(routines = testSet, currentRoutine = routine)
    }

    private var fetchJob: Job? = null

    fun fetchRoutines() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getRoutines()
            }.onSuccess { response ->
                val set = mutableSetOf<Routine>()
                response.body()?.result?.forEach { routine ->
                    set.add(routine.toRoutine())
                }
                _uiState.update { it.copy(
                    routines = set,
                    isLoading = false
                ) }
            }.onFailure { e ->
                _uiState.update { it.copy(
                    //message = e.message,
                    isLoading = false
                ) }
            }
        }
    }
}