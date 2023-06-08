package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.ui.theme.*

@Composable
fun DeviceCard (
    modifier : Modifier = Modifier,
    device : Device
){
    Card(
        modifier = modifier
            .padding(10.dp)
            .height(60.dp)
            .fillMaxWidth(),
            backgroundColor = Terciary,
            contentColor = Content,
            shape = RoundedCornerShape(15.dp),
            elevation = 10.dp,
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
                .background(Surface, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(8.dp),
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                )
            }
            Text(modifier = Modifier,
                text = device.name)
            Spacer(modifier = Modifier.padding(4.dp).size(50.dp))
        }
    }
}