package ar.edu.itba.homewizard.ui.routines

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.RoutinesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutineInfo(
    routinesViewModel: RoutinesViewModel
) {
    val routineUiState by routinesViewModel.uiState.collectAsState()
    val routine = routineUiState.currentRoutine
    val scope = rememberCoroutineScope()
    val mContext = LocalContext.current

    // TODO: Mover a estado
    val openDialog = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Schedule routine") // TODO: Change message
            },
            text = {
                Column() {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Text( stringResource(R.string.schedule_message))
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier,
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss") // TODO: Change message
                    }
                    Button(onClick = {
                        if(text.matches(Regex("^([0-5]?[0-9]|60)"))){
                            openDialog.value = false
                            Handler(Looper.getMainLooper()).postDelayed({
                                routinesViewModel.executeRoutine(routine!!)
                            }, text.toLong() * 1000)
                        } else {
                            Toast.makeText(mContext, "The number must be between 1 and 60", Toast.LENGTH_SHORT).show() // TODO: Change message
                        }
                    }
                    ) {
                        Text("Run") // TODO: Change message
                    }
                }
            }
        )
    }
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
                            contentDescription = "Back",
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
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.onPrimary

                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { routinesViewModel.executeRoutine(routine!!) },
                modifier= Modifier.padding(bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary),
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(
                    modifier = Modifier.size(80.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.play),
                    contentDescription = "content description",
                )
            }
            routine?.actions?.forEach { action ->
                ActionCard(action = action)
            }
        }
    }
}