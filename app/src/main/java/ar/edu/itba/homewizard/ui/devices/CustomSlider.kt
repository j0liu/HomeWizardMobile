package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.Secondary
import ar.edu.itba.homewizard.ui.theme.Terciary
import ar.edu.itba.homewizard.ui.theme.lightSurface

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    onValueChangeFinished: () -> Unit = {},
    unit: String,
    icon: Int,
) {
    Column(modifier = modifier
        .padding(12.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(20.dp).fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = lightSurface,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Slider(
                modifier = Modifier
                    .width(315.dp),
                value = value,
                onValueChange = onValueChange,
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = onValueChangeFinished,
                colors = SliderDefaults.colors(
                    thumbColor = lightSurface,
                    activeTrackColor = lightSurface,
                    inactiveTrackColor = Terciary,
                )
            )
            Icon(
                modifier = Modifier,
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = "content description",
                tint = lightSurface
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${value.toInt()}${unit}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = lightSurface,
            )
        }
    }
}