package ar.edu.itba.homewizard.ui.routines

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutinesScreen(routinesViewModel: RoutinesViewModel = viewModel()) {
    val routinesUiState by routinesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    // TODO: Modularizar?
    BackHandler(enabled = routinesUiState.scaffoldState.bottomSheetState.isExpanded) {
        scope.launch {
            routinesUiState.scaffoldState.bottomSheetState.collapse()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomSheetScaffold(
            modifier = Modifier.zIndex(10f),
            sheetShape = RoundedCornerShape(15.dp),
            scaffoldState = routinesUiState.scaffoldState,
            sheetContent = {
                RoutineInfo(routinesViewModel = routinesViewModel)
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                routinesUiState.routines.forEach { routine: Routine ->
                    RoutineCard(
                        routine = routine,
                        onClick = { routineSelected ->
                            routinesViewModel.setCurrentRoutine(routineSelected)
                            scope.launch {
                                routinesUiState.scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }
            }
        }
    }
}