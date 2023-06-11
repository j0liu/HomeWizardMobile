package ar.edu.itba.homewizard.ui.devices.blinds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.Secondary
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.devices.CustomSlider
import ar.edu.itba.homewizard.ui.theme.Content
import ar.edu.itba.homewizard.ui.theme.lightSurface

@Composable
fun BlindInfo() {
    var currentLevel by remember { mutableStateOf(0f) }
    var targetLevel by remember { mutableStateOf(0.75f) }

    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // Creating an Outlined Button and setting
            // the shape attribute to CircleShape
            // When the Button is clicked, a Toast
            // message would be displayed
            OutlinedButton(onClick = {},
                modifier= Modifier.size(100.dp),
                shape = RoundedCornerShape(25),
//                border= BorderStroke(5.dp, Color(0XFF0F9D58)),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor =  Primary)
            ) {
                // Adding an Icon "Add" inside the Button
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal),
                    contentDescription = "content description",
                    tint=Color.Black // TODO: Cambiar
                )
            }
            OutlinedButton(onClick = {},
                modifier= Modifier.size(100.dp),
                shape = RoundedCornerShape(25),
//                border= BorderStroke(5.dp, Color(0XFF0F9D58)),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor =  Primary)
            ) {
                // Adding an Icon "Add" inside the Button
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.blinds_horizontal_closed),
                    contentDescription = "content description",
                    tint=Color.Black // TODO: Cambiar
                )
            }
        }
        CustomSlider(
            value = currentLevel,
            onValueChange = { currentLevel = it },
            valueRange = 0f..100f,
            onValueChangeFinished = { /*TODO*/ },
            title = "Nivel de la persiana",
            unit = "",
            icon = R.drawable.blinds,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(),
            color = Secondary,
            progress = targetLevel
        )
//            Spacer(modifier = Modifier.width(40.dp))
    }
}