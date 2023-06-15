package ar.edu.itba.homewizard.ui.routines

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutineInfo(routinesViewModel: RoutinesViewModel) {
    val routineUiState by routinesViewModel.uiState.collectAsState()
    val routine = routineUiState.currentRoutine
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxHeight(0.95f),
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = routineUiState.currentRoutine?.name ?: "",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            scope.launch {
                                routineUiState.scaffoldState.bottomSheetState.collapse()
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp, 30.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.chevron_down),
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            routine?.actions?.forEach { action ->
                ActionCard(action = action)
            }
        }
    }
}