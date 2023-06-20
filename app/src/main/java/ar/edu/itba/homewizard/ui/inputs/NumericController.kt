package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R

@Composable
fun NumericController(value: Int, onValueChanged: (value: Int) -> Unit, unit: String = "", fontSize: Int = 70, multiplier: Float, startLimit: Int, endLimit: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        IconButton(
            onClick = { onValueChanged(value-1) },
            modifier = Modifier
                .size(70.dp*multiplier),
            enabled = value > startLimit
        ) {
            Icon(
                modifier = Modifier
                    .size(60.dp*multiplier),
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_horizontal_rule_24) ,
                tint = if(value > startLimit) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                contentDescription = stringResource(R.string.minus)
            )
        }
        Text(
            text = "${value}${unit}",
            fontSize = fontSize.sp*multiplier,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        )
        IconButton(
            onClick = { onValueChanged(value+1)},
            modifier = Modifier
                .size(70.dp*multiplier),
            enabled = value < endLimit
        ) {
            Icon(
                modifier = Modifier
                    .size(70.dp*multiplier),
                imageVector = Icons.Filled.Add,
                tint = if(value < endLimit) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                contentDescription = stringResource(R.string.plus)
            )
        }
    }
}