package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutinesHorizontal(mainViewModel: MainViewModel, routinesViewModel: RoutinesViewModel,
) {
    val routinesUiState by routinesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val mainUiState by mainViewModel.uiState.collectAsState()
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(routinesUiState.routines.toMutableList(), key = { routine: Routine -> routine.id  }) { routine : Routine ->
            RoutineCard(
                routine = routine,
                onClick = { selectedRoutine ->
                    routinesViewModel.setCurrentRoutine(selectedRoutine)
                    mainViewModel.setBottomBarVisibility(false)
                    scope.launch {
                        mainUiState.scaffoldState.bottomSheetState.expand()
                    }
                }
            )
        }
    }

}

