package ar.edu.itba.homewizard.ui.routines

import ar.edu.itba.homewizard.data.models.Routine

data class RoutinesUiState (
    val routines : MutableSet<Routine> = mutableSetOf(),
    var currentRoutine : Routine? = null,
    val isLoading: Boolean = false
)