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
    multiplier : Float = 1f,
    onClick : (Routine) -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp*multiplier)
            .height(80.dp*multiplier)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(15.dp*multiplier),
        elevation = 10.dp,
        onClick = { onClick(routine) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp*multiplier),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp*multiplier),
                text = routine.name,
                fontSize = 14.sp*multiplier
            )
            Row(
                modifier = Modifier.background(MaterialTheme.colors.surface, shape = RoundedCornerShape(10.dp*multiplier)).padding(4.dp*multiplier),
            ) {
                routine.actions.subList(0, routine.actions.size.coerceAtMost(6)).
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