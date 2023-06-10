package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.theme.Background
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.Surface
import ar.edu.itba.homewizard.ui.theme.Terciary

@Composable
fun CustomToggle(options : List<Int>, selected : Int, onSelectedChange : (Int) -> Unit) {
    Row (
        modifier = Modifier
            .padding(14.dp)
            .border(1.dp, color = Primary, shape = RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(50.dp))
    ){
        //Toggle button of three states
        options.forEachIndexed { index, icon ->
            Row(
                modifier = Modifier
                    .background(color = if (selected == index) Terciary else Surface)
                    .border(0.5.dp, color = Primary)
                    .height(70.dp)
                    .width(120.dp)
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
                        .size(50.dp),
                    imageVector = ImageVector.vectorResource(id = icon),
//                        tint = if (selected == index) lightSurface else Background,
                    contentDescription = "content description"
                )
            }
        }
    }
}