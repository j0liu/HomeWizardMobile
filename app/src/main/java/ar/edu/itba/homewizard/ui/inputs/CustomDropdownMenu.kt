package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropdownMenu(elements: List<String>, modifier: Modifier = Modifier, onSelected: (String) -> Unit, title: String = "", initialValue : String ) {
    var selectedText by remember { mutableStateOf(initialValue) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = title, color = MaterialTheme.colors.onSurface, fontSize = 11.sp) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Content,
                textColor = MaterialTheme.colors.surface
            ),
        )

        ExposedDropdownMenu(
            modifier = Modifier
                .height(200.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            elements.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedText = item
                    expanded = false
                    onSelected(item)
                }) {
                    Text(text = item)
                }
            }
        }
    }
}