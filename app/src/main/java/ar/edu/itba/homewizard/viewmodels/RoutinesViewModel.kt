package ar.edu.itba.homewizard.viewmodels

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.data.repository.RoutineRepository
import ar.edu.itba.homewizard.ui.routines.RoutinesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class RoutinesViewModel @Inject constructor(
    private val routineRepository : RoutineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState : StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _uiState.update { it.copy(
                    routines = routineRepository.getRoutines(),
                    isLoading = false
                ) }
            }
        }
    }

    fun setCurrentRoutine(routine : Routine) {
        _uiState.update {
            it.copy(
                currentRoutine = routine
            )
        }
    }

    fun executeRoutine(routine: Routine) {
        viewModelScope.launch {
            runCatching {
                routineRepository.executeRoutine(routine.id)
            }
        }

    }

    fun prevPage(){
        if (_uiState.value.currentPage == 0) return
        _uiState.update { it.copy(currentPage = it.currentPage - 1) }
    }

    fun nextPage(){
        if(_uiState.value.currentPage == _uiState.value.currentRoutine!!.actions.size/_uiState.value.itemsPerPage) return
        _uiState.update { it.copy(currentPage = it.currentPage + 1) }
    }

    fun setItemsPerPage(qtyItems : Int = 5){
        _uiState.update { it.copy(itemsPerPage = qtyItems) }
    }

    fun resetState(){
        _uiState.update { it.copy(
            currentRoutine = null,
            currentPage = 0
        ) }
    }
}