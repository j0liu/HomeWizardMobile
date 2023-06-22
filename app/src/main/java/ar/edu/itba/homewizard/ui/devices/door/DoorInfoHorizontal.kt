package ar.edu.itba.homewizard.ui.devices.door

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.models.devices.Door
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.R



@Composable
fun DoorInfoHorizontal(devicesViewModel: DevicesViewModel, door : Door, multiplier: Float = 1f){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp*multiplier)
    ) {
        Button(
            onClick = { door.toggleLock(devicesViewModel) },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
            shape = RoundedCornerShape(20.dp*multiplier)
        ) {
            Icon(
                modifier = Modifier
                    .size(120.dp*multiplier),
                imageVector =
                if (door.lock) ImageVector.vectorResource(id = R.drawable.lock)
                else ImageVector.vectorResource(id = R.drawable.lock_open),
                tint = MaterialTheme.colors.onSurface,
                contentDescription = stringResource(R.string.lock)
            )
        }
        Button(
            onClick = { door.toggleOpenClose(devicesViewModel) },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
            shape = RoundedCornerShape(20.dp*multiplier)
        ) {
            Icon(
                modifier = Modifier
                    .size(120.dp*multiplier),
                imageVector =
                if (door.status) ImageVector.vectorResource(id = R.drawable.door_closed)
                else ImageVector.vectorResource(id = R.drawable.door_open),
                tint = MaterialTheme.colors.onSurface,
                contentDescription = stringResource(R.string.door)
            )
        }
    }
}