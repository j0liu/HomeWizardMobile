package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.Routine
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutinesScreen(routinesViewModel: RoutinesViewModel = viewModel()) {
    val routinesUiState by routinesViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Primary),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    routinesUiState.currentRoutine?.let { Text(it.name) }
                }
            }) {
            // app UI
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
                                scaffoldState.bottomSheetState.expand()

                            }
                        }
                    )
                }
                Button(
                    content = { Text("Add Routine") },
                    onClick = { routinesViewModel.addRoutine() }
                )
            }
        }
    }
}