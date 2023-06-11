package ar.edu.itba.homewizard.ui.devices.ac

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.inputs.CustomToggle
import ar.edu.itba.homewizard.ui.inputs.DropdownButton
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.viewmodels.ACViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ACInfo(acViewModel: ACViewModel = viewModel()) {
    Column (
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
                    .size(100.dp)
                    .background(Surface, shape = CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_power_settings_new_24),
                    contentDescription = "content description"
                )
            }
        }
        // TODO: Move to state
        val options = listOf(R.drawable.white_balance_sunny, R.drawable.snowflake, R.drawable.weather_windy)
        var selected by remember { mutableStateOf(0) }
       CustomToggle(options = options, selected = selected, onSelectedChange = {  selected = it })
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            DropdownButton(modifier = Modifier, "Velocidad\nventilador", listOf("25", "50", "75", "100"), 118)
            DropdownButton(modifier = Modifier, "Aspas\nverticales", listOf("Auto", "22", "45", "67", "90"), 118)
            DropdownButton(modifier = Modifier, "Aspas\nhorizontales", listOf("Auto", "-90", "-45", "0", "45", "90"), 118)
        }

    }
}