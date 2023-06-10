package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.viewmodels.LampViewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.viewmodels.ACViewModel
import io.mhssn.colorpicker.ColorPicker
import io.mhssn.colorpicker.ColorPickerType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ACInfo(acViewModel: ACViewModel = viewModel()) {
    Column (
        modifier = Modifier
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //rounded button with material design icon
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(100.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_horizontal_rule_24) ,
                    tint = lightSurface,
                    contentDescription = "content description"
                )
            }
            Text(
                text = "24°",
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold,
                color = lightSurface,
                modifier = Modifier
            )
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(100.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(70.dp),
                    imageVector = Icons.Filled.Add,
                    tint = lightSurface,
                    contentDescription = "content description"
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(110.dp)
                    .background(Surface, shape = CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_power_settings_new_24),
                    contentDescription = "content description"
                )
            }
        }
        Row (
            modifier = Modifier
                .padding(14.dp)
                .clip(RoundedCornerShape(50.dp))
        ){
            //Toggle button of three states
            var selected by remember { mutableStateOf(0) }
            val options = listOf(R.drawable.baseline_wb_sunny_24, R.drawable.baseline_ac_unit_24, R.drawable.baseline_air_24)
            options.forEachIndexed { index, icon ->
                Row(
                    modifier = Modifier
                        .background(color = if (selected == index) Background else Surface,)
                        .height(70.dp)
                        .width(120.dp)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                selected = index
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
        Row(
            Modifier.padding(8.dp)
        ) {
            DropdownButton(modifier = Modifier, "Velocidad\nventilador", listOf("25", "50", "75", "100"))
            DropdownButton(modifier = Modifier, "Aspas\nverticales", listOf("Auto", "22", "45", "67", "90"))
            DropdownButton(modifier = Modifier, "Aspas\nhorizontales", listOf("Auto", "-90", "-45", "0", "45", "90"))
        }

    }
}