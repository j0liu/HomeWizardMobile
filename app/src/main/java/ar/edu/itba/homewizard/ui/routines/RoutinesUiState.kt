package ar.edu.itba.homewizard.ui.routines

import ar.edu.itba.homewizard.data.Routine

class RoutinesUiState (
    val routines : MutableSet<Routine> = mutableSetOf(),
    var currentRoutine : Routine? = null
)