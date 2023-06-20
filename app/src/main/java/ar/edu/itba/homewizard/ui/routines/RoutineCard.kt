package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.data.models.Routine

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutineCard(
    modifier : Modifier = Modifier,
    routine : Routine,
    onClick : (Routine) -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .height(100.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp,
        onClick = { onClick(routine) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = routine.name,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier.background(MaterialTheme.colors.surface, shape = RoundedCornerShape(10.dp)).padding(4.dp),
            ) {
                routine.actions.subList(0, routine.actions.size.coerceAtMost(7)).
                forEach { action ->
                    Icon(
                        modifier = Modifier.padding(4.dp),
                        imageVector = ImageVector.vectorResource(action.device.type.icon),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                }

            }
        }
    }
}