package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.data.models.Device

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeviceCard (
    modifier : Modifier = Modifier,
    device : Device,
    multiplier : Float = 1f,
    onClick : (Device) -> Unit
){
    Card(
        modifier = modifier
            .padding(10.dp)
            .height(50.dp*multiplier)
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
                .padding(10.dp*multiplier)
                .size(40.dp*multiplier)
                .aspectRatio(1f)
                .background(MaterialTheme.colors.surface, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(10.dp),
                    imageVector = ImageVector.vectorResource(device.type.icon),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }
            Text(modifier = Modifier,
                text = device.name,
                textAlign = TextAlign.Center,
                fontSize = 14.sp*multiplier,)
            Spacer(modifier = Modifier.padding(4.dp).size(50.dp))
        }
    }
}