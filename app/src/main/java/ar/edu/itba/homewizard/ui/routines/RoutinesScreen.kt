package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.ui.LoadingAnimation
import ar.edu.itba.homewizard.ui.utils.ScreenSize
import ar.edu.itba.homewizard.ui.inputs.CustomDialog
import ar.edu.itba.homewizard.ui.inputs.CustomDropdownMenu
import ar.edu.itba.homewizard.ui.utils.SortingCriterias
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
    mainViewModel.setAfterCollapseBottomSheetAction {
        routinesViewModel.resetState()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomSheetScaffold(
            topBar = {RoutinesTopBar(routinesViewModel = routinesViewModel, routinesUiState = routinesUiState)},
            sheetShape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
            scaffoldState = mainUiState.scaffoldState,
            sheetContent = {
                RoutineInfo(routinesViewModel = routinesViewModel, mainViewModel = mainViewModel)
            }) {
            if (routinesUiState.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingAnimation()
                }
            } else {
                BoxWithConstraints {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(
                            if(maxWidth < maxHeight) {
                                if(maxHeight > ScreenSize.tabletHeight) 2 else 1
                            }
                            else {
                                if(maxWidth > ScreenSize.tabletWidth) 3 else 2
                            }
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 16.dp, bottom = 56.dp)
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
}

@Composable
private fun RoutinesTopBar(routinesViewModel: RoutinesViewModel, routinesUiState: RoutinesUiState) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = stringResource(R.string.routines),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {},
        actions = {
            IconButton(onClick = {
                routinesViewModel.setSortDialogOpen(true)
            }) {
                Icon(
                    modifier = Modifier.size(30.dp, 30.dp),
                    imageVector =  ImageVector.vectorResource(R.drawable.filter_variant),
                    contentDescription = "Back"
                )
            }
            if (routinesUiState.currentRoutine === null) {
                CustomDialog( openDialog = routinesUiState.sortDialogIsOpen, onClosureRequest = { routinesViewModel.setSortDialogOpen(false) },
                    title = "${stringResource(R.string.order)} ${stringResource(R.string.routines)}", submitText = stringResource(
                        R.string.order),
                    onSubmit = {
                        routinesViewModel.sortRoutines()
                        true
                    }
                ) {
                    Column(
                        modifier = Modifier.background(color = MaterialTheme.colors.primary)
                    ) {
                        CustomDropdownMenu(
                            modifier = Modifier.background(color = MaterialTheme.colors.surface, shape = RectangleShape),
                            elements = SortingCriterias.sortingCriteriaNames,
                            title = stringResource(R.string.orderby),
                            selected = routinesUiState.sortCriteriaName
                        ){routinesViewModel.setOrderCriteria(it)}
                    }
                }
            }

        }
    )
}