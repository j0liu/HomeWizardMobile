package ar.edu.itba.homewizard.viewmodels

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.data.repository.RoutineRepository
import ar.edu.itba.homewizard.ui.routines.RoutinesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
class RoutinesViewModel(private val routineRepository : RoutineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState : StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    var routines : MutableSet<Routine> = mutableSetOf()

    init {
        viewModelScope.launch {
            runCatching {
                routineRepository.getRoutines().forEach { routine ->
                    routines.add(routine)
                }
                _uiState.update { it.copy(
                    routines = routines,
                    isLoading = false
                ) }
            }
        }
    }

    fun setCurrentRoutine(routine : Routine) {
        _uiState.value = RoutinesUiState(routines = routines, currentRoutine = routine)
    }

}