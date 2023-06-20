package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropdownMenu(modifier: Modifier = Modifier, elements: List<String>, selected : String = elements[0], title: String = "", onSelected: (String) -> Unit) {
    var selectedText by remember { mutableStateOf(if (selected in elements) selected else elements[0]) }
    var expanded by remember { mutableStateOf(false) }
    val mContext = LocalContext.current

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        val selectedId = mContext.resources.getIdentifier(selectedText, "string",mContext.packageName)
        val selected = mContext.resources.getString(selectedId)
        TextField(
            value = selected,
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
            modifier = Modifier.heightIn(max = 320.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            elements.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedText = item
                    expanded = false
                    onSelected(item)
                }) {
                    val textId = mContext.resources.getIdentifier(item, "string",mContext.packageName)
                    val text = mContext.resources.getString(textId)
                    Text(text = text)
                }
            }
        }
    }
}