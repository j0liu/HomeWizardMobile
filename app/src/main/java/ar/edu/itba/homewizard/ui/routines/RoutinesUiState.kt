package ar.edu.itba.homewizard.ui.routines

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.models.Routine

data class RoutinesUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    val routines : List<Routine> = listOf(),
    var currentRoutine : Routine? = null,
    val isLoading: Boolean = false,
    var scaffoldState: BottomSheetScaffoldState = BottomSheetScaffoldState(
        DrawerState(DrawerValue.Closed),
        BottomSheetState(BottomSheetValue.Collapsed), // TODO: Ver si hace falta collapse
        SnackbarHostState()
    )
)