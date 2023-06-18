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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R

@Composable
fun NumericController(value: Int, onValueChanged: (value: Int) -> Unit, unit: String = "", fontSize: Int = 70) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        IconButton(
            onClick = { onValueChanged(value-1) },
            modifier = Modifier
                .size(70.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(60.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_horizontal_rule_24) ,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = "content description"
            )
        }
        Text(
            text = "${value}${unit}",
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        )
        IconButton(
            onClick = { onValueChanged(value+1)},
            modifier = Modifier
                .size(70.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(70.dp),
                imageVector = Icons.Filled.Add,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = "content description"
            )
        }
    }
}