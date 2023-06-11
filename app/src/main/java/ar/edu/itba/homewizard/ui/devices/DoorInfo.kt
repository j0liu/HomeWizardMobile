package ar.edu.itba.homewizard.ui.devices

import android.widget.ToggleButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.lightSurface
import ar.edu.itba.homewizard.viewmodels.DoorViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DoorInfo (doorViewModel: DoorViewModel = viewModel()){
    val devicesUiState by doorViewModel.uiState.collectAsState()
        Card(modifier = Modifier
            .fillMaxHeight(0.6f)
            .width(200.dp)
            .padding(top = 150.dp), shape = RoundedCornerShape(20.dp)) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { doorViewModel.toggleLock() },
                    Modifier.padding(bottom = 40.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(100.dp),
                        imageVector = if (devicesUiState.locked) Icons.Filled.Lock else Icons.Filled.Done,
                        tint = Color.Black,
                        contentDescription = "content description"
                    )
                }
                Button(
                    onClick = { doorViewModel.toggleClose() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = RoundedCornerShape(20.dp)
                    ) {
                    Icon(
                        modifier = Modifier
                            .size(100.dp),
                        imageVector = if (devicesUiState.closed) Icons.Filled.Close else Icons.Filled.Create,
                        tint = Color.Black,
                        contentDescription = "content description"
                    )
                }
        }
    }
}