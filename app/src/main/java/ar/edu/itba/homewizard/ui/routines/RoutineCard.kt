package ar.edu.itba.homewizard.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.data.Routine
import ar.edu.itba.homewizard.ui.theme.Content
import ar.edu.itba.homewizard.ui.theme.Surface
import ar.edu.itba.homewizard.ui.theme.Terciary

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
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp,
        onClick = { onClick(routine) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = routine.name,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier.background(MaterialTheme.colors.surface, shape = RoundedCornerShape(10.dp)).padding(8.dp),
            ) {
                routine.actions.forEach { action ->
                    Icon(
                        modifier = Modifier.fillMaxHeight().padding(4.dp),
                        imageVector = ImageVector.vectorResource(action.device.type.icon),
                        contentDescription = null
                    )
                }

            }
        }
    }
}