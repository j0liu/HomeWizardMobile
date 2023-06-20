package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun CustomToggle(options : List<Int>, selected : Int, modifier: Modifier = Modifier, multiplier : Float = 1f, onSelectedChange : (Int) -> Unit) {
    Row (
        modifier = Modifier.then(modifier)
            .border(1.dp*multiplier, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(50.dp*multiplier))
            .clip(RoundedCornerShape(50.dp*multiplier)),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        options.forEachIndexed { index, icon ->
            Row(
                modifier = Modifier
                    .background(color = if (selected == index) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.surface)
                    .border(0.5.dp, color = MaterialTheme.colors.primary).fillMaxHeight().weight(1f)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            onSelectedChange(index)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp * multiplier),
                    imageVector = ImageVector.vectorResource(id = icon),
                        tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(R.string.icon)
                )
            }
        }
    }
}