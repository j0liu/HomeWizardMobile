package ar.edu.itba.homewizard.ui.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.data.Device
import ar.edu.itba.homewizard.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownButton (
    modifier : Modifier = Modifier,
    title : String,
    options : List<String>,
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = lightSurface,
            modifier = Modifier
        )
        var expanded by remember { mutableStateOf(false) }
        val selectedIndex = remember { mutableStateOf(0) }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = Surface)
                .height(50.dp)
                .width(100.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        expanded = true
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = options[selectedIndex.value],
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(10.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(color = Surface)
                    .height(122.dp)
            ) {
                options.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex.value = index
                        expanded = false
                    }) {
                        Text(text = s)
                    }
                }
            }
        }
    }
}