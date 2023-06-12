package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.data.Action
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.ui.theme.Content
import ar.edu.itba.homewizard.ui.theme.Surface
import ar.edu.itba.homewizard.ui.theme.Terciary

@Composable
fun ActionCard(
    modifier : Modifier = Modifier,
    action : Action
) {
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
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .aspectRatio(1f)
                    .background(Surface, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(8.dp),
                    imageVector = ImageVector.vectorResource(action.device.type.icon),
                    contentDescription = null,
                )
            }
            Column {
                Text(text = action.device.name)
                Row {
                    Text(text = action.actionName)
                    action.params.forEach {x ->
                        Text(modifier = Modifier.padding(start = 10.dp),
                            text = x.toString())
                    }
                }
            }
            //Spacer(modifier = Modifier.padding(4.dp).size(50.dp))
        }
    }
}