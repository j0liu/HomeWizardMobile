package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import ar.edu.itba.homewizard.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownButton (
    modifier : Modifier = Modifier,
    title : String,
    titleSize : Int,
    options : List<String>,
){
    var expanded by remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ){
        Text(
            text = title,
            fontSize = titleSize.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = lightSurface,
            modifier = Modifier
                .weight(1f)
        )
        Column (
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(50.dp))
                .width(150.dp)
                .background(color = Surface)
                .height(45.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        expanded = true
                    }
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = options[selectedIndex.value],
                fontSize = 18.sp,
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
            ) {
                options.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex.value = index
                        expanded = false
                    }) {
                        Text(text = s, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}