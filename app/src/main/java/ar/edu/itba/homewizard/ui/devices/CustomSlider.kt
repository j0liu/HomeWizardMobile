package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun CustomSlider(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChangeFinished: () -> Unit = {},
    unit: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(20.dp)
        ) {
            Text(text = title) // (1)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                modifier = Modifier
                    .width(280.dp)
                    .padding(end = 4.dp),
                value = value,
                onValueChange = onValueChange,
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = onValueChangeFinished
            )
            Icon(
                modifier = Modifier
                    .padding(end = 12.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_thermostat_24),
                contentDescription = "content description"
            )
            Text(
                modifier = Modifier,
                text = "${value.toInt()}${unit}"
            )
        }
    }
}