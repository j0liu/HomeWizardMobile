package ar.edu.itba.homewizard.ui.routines

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.data.models.Routine
import ar.edu.itba.homewizard.viewmodels.RoutineInfoViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutineInfo(routineInfoViewModel: RoutineInfoViewModel = viewModel()) {
    val routine = Routine("1", "a dormir", listOf(
//        Action("turnoff", Device("3", "luz", DeviceType.deviceTypes["lamp"]!!, {}), listOf(1, 2, 3)),
//        Action("turnon", Device("3", "luz", DeviceType.deviceTypes["oven"]!!, {}), emptyList()),
//        Action("setBrightness", Device("3", "luz", DeviceType.deviceTypes["oven"]!!, {}), emptyList())
    ), "a")
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                /* FAB content */
            }
        }
    ) {
        Column(
            //modifier = Modifier.align(Alignment.Center)
        ) {
            routine.actions.forEach { action ->
                ActionCard(action = action)
//                showAsBottomSheet

            }
        }
    }

}