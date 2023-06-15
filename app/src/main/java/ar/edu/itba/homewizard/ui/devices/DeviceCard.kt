package ar.edu.itba.homewizard.ui.devices

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeviceCard (
    modifier : Modifier = Modifier,
    device : Device,
    onClick : (Device) -> Unit
){
    Card(
        modifier = modifier
            .padding(10.dp)
            .height(60.dp)
            .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.onSurface,
            shape = RoundedCornerShape(15.dp),
            elevation = 10.dp,
            onClick = { onClick(device) }
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .padding(10.dp)
                .size(50.dp)
                .aspectRatio(1f)
                .background(MaterialTheme.colors.surface, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(8.dp),
                    imageVector = ImageVector.vectorResource(device.type.icon),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }
            Text(modifier = Modifier,
                text = device.name)
            Spacer(modifier = Modifier.padding(4.dp).size(50.dp))
        }
    }
}