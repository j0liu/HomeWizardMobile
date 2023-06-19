package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropdownMenu(modifier: Modifier = Modifier, elements: List<String>, selected : String = elements[0], title: String = "", onSelected: (String) -> Unit) {
    var selectedText by remember { mutableStateOf(if (selected in elements) selected else elements[0]) }
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
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface
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