package ar.edu.itba.homewizard.ui.routines

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.utils.ScreenSize
import ar.edu.itba.homewizard.viewmodels.MainViewModel
import ar.edu.itba.homewizard.ui.inputs.CustomDialog
import ar.edu.itba.homewizard.ui.inputs.PaginationArrows
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutineInfo(
    mainViewModel: MainViewModel,
    routinesViewModel: RoutinesViewModel
) {
    val routineUiState by routinesViewModel.uiState.collectAsState()
    val routine = routineUiState.currentRoutine
    val scope = rememberCoroutineScope()
    val mContext = LocalContext.current

    // TODO: Mover a estado
    val openDialog = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }


    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }

    val configuration = LocalConfiguration.current
    val validationTimerString = stringResource(R.string.validation_timer)

// If our configuration changes then this will launch a new coroutine scope for it
    LaunchedEffect(configuration) {
        // Save any changes to the orientation value on the configuration object
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    routinesViewModel.setItemsPerPage(
        if(orientation == ORIENTATION_LANDSCAPE)
            if (LocalConfiguration.current.screenWidthDp.dp > ScreenSize.tabletWidth) 10 else 4
        else
            if (LocalConfiguration.current.screenHeightDp.dp > ScreenSize.tabletHeight) 20 else 4
    )

    CustomDialog(openDialog = openDialog.value, onClosureRequest = {openDialog.value = false},
        title = "${stringResource(R.string.schedule)} ${stringResource(R.string.routine)}", submitText = stringResource(R.string.schedule),
        onSubmit = {
            val submit = text.matches(Regex("^([0-5]?[0-9]|60)"))
            if(submit){
                Handler(Looper.getMainLooper()).postDelayed({
                    routinesViewModel.executeRoutine(routine!!)
                }, text.toLong() * 1000)
            } else {
                Toast.makeText(mContext, validationTimerString, Toast.LENGTH_SHORT).show()
            }
            submit
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(modifier = Modifier.background(MaterialTheme.colors.onPrimary), colors = TextFieldDefaults.textFieldColors(MaterialTheme.colors.onSurface), value = text, onValueChange = { text = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true)
            Text(stringResource(R.string.schedule_message))
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxHeight(0.99f),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
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
                                mainViewModel.collapseBottomSheet(scope)
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp, 30.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.chevron_down),
                            contentDescription = stringResource(R.string.down),
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            openDialog.value = true

                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp, 30.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.clock_outline),
                            contentDescription = stringResource(R.string.clock),
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Button(
                    onClick = { routinesViewModel.executeRoutine(routine!!) },
                    modifier= Modifier.padding(top = 20.dp, bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.play),
                        contentDescription = stringResource(R.string.execute_routine),
                    )
                }

                BoxWithConstraints {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(
                            if(orientation == ORIENTATION_LANDSCAPE)
                                if (LocalConfiguration.current.screenWidthDp.dp > ScreenSize.tabletWidth) 3 else 2
                            else
                                if (LocalConfiguration.current.screenHeightDp.dp > ScreenSize.tabletHeight) 2 else 1
                        ),
                        modifier = Modifier
                            .padding(end = 10.dp)
                    ) {
                        if(routine != null && routine.actions.toMutableList().size != 0){
                            items(
                                routine.actions.toMutableList().subList(
                                    routineUiState.currentPage * routineUiState.itemsPerPage,
                                    ((routineUiState.currentPage + 1) * routineUiState.itemsPerPage).coerceAtMost(routine.actions.size)
                                )
                            ){ action ->
                                ActionCard(action = action, multiplier = if(maxHeight > ScreenSize.tabletHeight && maxWidth > ScreenSize.tabletWidth) 1.8f else 1.3f)
                            }
                        }
                    }
                }
            }
            if(routineUiState.currentRoutine != null)
                PaginationArrows(currentPage = routineUiState.currentPage, maxPage = (routineUiState.currentRoutine!!.actions.size - 1)/routineUiState.itemsPerPage, modifier = Modifier, onLeftClick = {routinesViewModel.prevPage()}, onRightClick = {routinesViewModel.nextPage()})
        }
    }
}
