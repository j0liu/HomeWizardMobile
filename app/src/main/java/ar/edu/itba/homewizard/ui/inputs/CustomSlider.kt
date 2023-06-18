package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    onValueChangeFinished: (Float) -> Unit = {},
    unit: String,
    icon: Int,
) {
    var currentValue by remember { mutableStateOf(value) }

    Column(
        modifier = Modifier
            .padding(start = 10.dp)
    )
    {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(20.dp).fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Slider(
                modifier = Modifier
                    .weight(0.8f),
                value = currentValue,
                onValueChange = { currentValue = it },
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = {onValueChangeFinished(currentValue)},
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.surface,
                    activeTrackColor = MaterialTheme.colors.surface,
                    inactiveTrackColor = MaterialTheme.colors.primaryVariant,
                )
            )
            Icon(
                modifier = Modifier
                    .weight(0.1f),
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = "content description",
                tint = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = Modifier
                    .weight(0.1f),
                maxLines = 1,
                text = "${value.toInt()}${unit}",
                fontSize = 18.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}