package ar.edu.itba.homewizard.ui.inputs

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.ui.theme.*
import ar.edu.itba.homewizard.ui.utils.Translate

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownButton (
    modifier : Modifier = Modifier,
    title : String,
    titleSize : Int,
    options : Array<String>,
    initialValue: String,
    multiplier : Float = 1f,
    onOptionSelected: (value: String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(options.indexOf(initialValue)) }
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp*multiplier)
    ){
        Text(
            text = title,
            fontSize = titleSize.sp*multiplier,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .weight(1f)
        )
        Column (
            modifier = Modifier
                .padding(10.dp*multiplier)
                .clip(RoundedCornerShape(50.dp*multiplier))
                .width(150.dp*multiplier)
                .background(color = MaterialTheme.colors.surface)
                .height(45.dp*multiplier)
                .pointerInput(Unit) {
                    detectTapGestures {
                        expanded = true
                    }
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = Translate(context, options[selectedIndex]),
                fontSize = 18.sp*multiplier,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(10.dp*multiplier)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(color = MaterialTheme.colors.surface)
            ) {
                options.forEachIndexed { index, _ ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        expanded = false
                        onOptionSelected(options[index])
                    }) {
                        Text(text = Translate(context, options[index]), textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}