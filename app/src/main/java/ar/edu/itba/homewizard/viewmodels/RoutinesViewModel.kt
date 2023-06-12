package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import ar.edu.itba.homewizard.data.Action
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.DeviceType
import ar.edu.itba.homewizard.data.Routine
import ar.edu.itba.homewizard.ui.devices.DevicesUiState
import ar.edu.itba.homewizard.ui.routines.RoutinesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RoutinesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState : StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    var testSet : MutableSet<Routine> = mutableSetOf()

    //Aca se arma la logica que pide a la api, este llamado actualiza Devices (esta en uiState)

    fun addRoutine(){ //example
        testSet.add(Routine("1", "a dormir", arrayOf(
            Action("1", Device("3", "luz",DeviceType.deviceTypes["lamp"]!!, {}), emptyArray(), "a"),
            Action("1", Device("3", "luz", DeviceType.deviceTypes["oven"]!!, {}), emptyArray(), "a")
        ), "a"))
        _uiState.value = RoutinesUiState(routines = testSet)
    }

    fun setCurrentRoutine(routine : Routine) {
        _uiState.value = RoutinesUiState(routines = testSet, currentRoutine = routine)
    }
}