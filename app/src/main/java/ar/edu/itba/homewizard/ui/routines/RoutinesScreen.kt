package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.layout.*
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
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutinesScreen(
    mainViewModel: MainViewModel,
    routinesViewModel: RoutinesViewModel = hiltViewModel(),
) {
    val mainUiState by mainViewModel.uiState.collectAsState()
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
                if(maxWidth < maxHeight) {
                    RoutinesVertical(mainViewModel = mainViewModel, routinesViewModel = routinesViewModel)
                } else {
                    RoutinesHorizontal(mainViewModel = mainViewModel, routinesViewModel = routinesViewModel)
                }
            }

        }
    }
}