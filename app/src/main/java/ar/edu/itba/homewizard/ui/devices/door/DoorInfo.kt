package ar.edu.itba.homewizard.ui.devices.door

import android.widget.ToggleButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import ar.edu.itba.homewizard.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.lightSurface
import ar.edu.itba.homewizard.viewmodels.DoorViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ar.edu.itba.homewizard.ui.theme.Surface

@Composable
fun DoorInfo (doorViewModel: DoorViewModel = viewModel()){
    val devicesUiState by doorViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Button(
            onClick = { doorViewModel.toggleLock() },
            Modifier.padding(bottom = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = lightSurface),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(120.dp),
                imageVector =
                if (devicesUiState.locked) ImageVector.vectorResource(id = R.drawable.lock)
                else ImageVector.vectorResource(id = R.drawable.lock_open),
                tint = Color.Black,
                contentDescription = "content description"
            )
        }
        Button(
            onClick = { doorViewModel.toggleClose() },
            colors = ButtonDefaults.buttonColors(backgroundColor = lightSurface),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(120.dp),
                imageVector =
                if (devicesUiState.closed) ImageVector.vectorResource(id = R.drawable.door_closed)
                else ImageVector.vectorResource(id = R.drawable.door_open),
                tint = Color.Black,
                contentDescription = "content description"
            )
        }
    }
}