package ar.edu.itba.homewizard.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.bridges.SnackbarBridge
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.data.repository.RoutineRepository
import ar.edu.itba.homewizard.ui.routines.RoutinesUiState
import ar.edu.itba.homewizard.ui.utils.SharedPreferencesUtils
import ar.edu.itba.homewizard.ui.utils.SortingCriterias
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutinesViewModel @Inject constructor(
    @ApplicationContext private val context : Context,
    private val snackbarBridge: SnackbarBridge,
    private val routineRepository : RoutineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState : StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            runCatching {
                val sortCriteriaName = Contexts.getApplication(context).getSharedPreferences(
                    SharedPreferencesUtils.SORT_ORDER_KEY,Context.MODE_PRIVATE)
                    .getString(SharedPreferencesUtils.ROUTINE_SORT_ORDER_KEY, SortingCriterias.BY_USAGE)!!

                _uiState.update {
                    it.copy(
                        routines = routineRepository.getRoutines(),
                        sortCriteriaName = sortCriteriaName
                    )
                }
                sortRoutines()
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun setCurrentRoutine(routine : Routine) {
        _uiState.update { it.copy(isLoading = true) }
        _uiState.update {
            it.copy(
                currentRoutine = routine,
                isLoading = false
            )
        }
    }

    fun executeRoutine(routine: Routine) {
        viewModelScope.launch {
            runCatching {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                routineRepository.executeRoutine(routine.id)
                routine.qtyUses++
                routineRepository.updateRoutine(routine)
                snackbarBridge.sendMessage(context.getString(R.string.executed_routine))
                _uiState.update {
                    it.copy(isLoading = false)
                }
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

    fun sortRoutines() {
        _uiState.update {
            it.copy(
                sortCriteria = Routine.orderCriterias[it.sortCriteriaName]!!
            )
        }
        _uiState.update {
            it.copy(routines = it.sortRoutines())
        }
    }

    fun setSortDialogOpen(isOpen: Boolean) {
        _uiState.update {
            it.copy(
                sortDialogIsOpen = isOpen
            )
        }
    }
    fun setOrderCriteria(name : String) {
        Contexts.getApplication(context)
            .getSharedPreferences(SharedPreferencesUtils.SORT_ORDER_KEY, Context.MODE_PRIVATE)
            .edit()
            .putString(SharedPreferencesUtils.ROUTINE_SORT_ORDER_KEY, name)
            .apply()
        _uiState.update {
            it.copy(
                sortCriteriaName = name
            )
        }
    }
}