package ar.edu.itba.homewizard.ui.routines

import ar.edu.itba.homewizard.data.models.Routine

data class RoutinesUiState (
    val routines : List<Routine> = listOf(),
    var currentRoutine : Routine? = null,
    val isLoading: Boolean = false,
    var itemsPerPage : Int = 5,
    var currentPage: Int = 0,

    val sortCriteria : Comparator<Routine> = Routine.orderCriterias["Uses"]!!,
    val sortCriteriaName : String = "Uses",
    var sortDialogIsOpen : Boolean = false
) {
    fun sortRoutines(routines : List<Routine> = this.routines) : List<Routine> {
        return routines.sortedWith(sortCriteria)
    }
}