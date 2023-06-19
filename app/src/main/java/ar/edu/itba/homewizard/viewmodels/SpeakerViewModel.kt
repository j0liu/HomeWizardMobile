package ar.edu.itba.homewizard.viewmodels

import androidx.lifecycle.ViewModel
import ar.edu.itba.homewizard.ui.devices.speaker.SpeakerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpeakerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SpeakerUiState())
    val uiState : StateFlow<SpeakerUiState> = _uiState.asStateFlow()

    fun togglePlay(){
        _uiState.value = SpeakerUiState(!uiState.value.playing)
    }
    fun prev(){
        _uiState.value = SpeakerUiState()
    }
    fun next(){
        _uiState.value = SpeakerUiState()
    }
    fun setVolume(volume : Float){
        _uiState.value = SpeakerUiState(volume = volume)
    }

    fun setGenre(genre : String){
        _uiState.value = SpeakerUiState(genre = genre)
    }
}