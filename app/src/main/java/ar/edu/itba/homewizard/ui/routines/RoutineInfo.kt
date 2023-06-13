package ar.edu.itba.homewizard.ui.routines

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.Action
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.data.DeviceType
import ar.edu.itba.homewizard.data.Routine
import ar.edu.itba.homewizard.viewmodels.ACViewModel
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