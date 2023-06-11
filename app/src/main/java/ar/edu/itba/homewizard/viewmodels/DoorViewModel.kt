package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import ar.edu.itba.homewizard.ui.devices.door.DoorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DoorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DoorUiState())
    val uiState : StateFlow<DoorUiState> = _uiState.asStateFlow()

    fun toggleLock(){
        _uiState.update{ oldState -> DoorUiState(!oldState.locked, oldState.closed) }
    }


    fun toggleClose(){
        _uiState.update{ oldState -> DoorUiState(oldState.locked, !oldState.closed) }
    }

}