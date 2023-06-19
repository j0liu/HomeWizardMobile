package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutinesScreen(
    mainViewModel: MainViewModel,
    routinesViewModel: RoutinesViewModel = hiltViewModel(),
) {
    val mainUiState by mainViewModel.uiState.collectAsState()
    val routinesUiState by routinesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    mainViewModel.setBackHandler(scope)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomSheetScaffold(
            sheetShape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
            scaffoldState = mainUiState.scaffoldState,
            sheetContent = {
                RoutineInfo(routinesViewModel = routinesViewModel, mainViewModel = mainViewModel)
            }) {

            BoxWithConstraints {
                /*
                if(maxWidth < maxHeight) {
                    RoutinesVertical(mainViewModel = mainViewModel, routinesViewModel = routinesViewModel)
                } else {
                    RoutinesHorizontal(mainViewModel = mainViewModel, routinesViewModel = routinesViewModel)
                }*/


                LazyVerticalGrid(
                    columns = GridCells.Fixed(if (maxWidth < maxHeight) 1 else 2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(
                        routinesUiState.routines.toMutableList(),
                        key = { routine: Routine -> routine.id }) { routine: Routine ->
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
        }
    }
}