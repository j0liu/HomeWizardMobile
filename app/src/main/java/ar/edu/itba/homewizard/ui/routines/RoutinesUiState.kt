package ar.edu.itba.homewizard.ui.routines

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.models.Routine

data class RoutinesUiState (
    val routines : List<Routine> = listOf(),
    var currentRoutine : Routine? = null,
    val isLoading: Boolean = false,
)