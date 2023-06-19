package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R

@Composable
fun PasswordInput(label : String, text : TextFieldValue, onValueChange : (TextFieldValue) -> Unit, modifier : Modifier = Modifier, multiplier : Float = 1f){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier.then(modifier),
        visualTransformation =  if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = { Text(label, fontSize = 15.sp*multiplier) },
        label = { Text(label, fontSize = 12.sp*multiplier) },
        singleLine = true,
        textStyle = (MaterialTheme.typography.body2).copy(fontSize = 16.sp*multiplier),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface
        ),
        trailingIcon = {
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(
                    modifier = Modifier.fillMaxHeight().padding(12.dp*multiplier),
                    imageVector = ImageVector.vectorResource(id = if(passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                    contentDescription = "content description"
                )
            }
        }
    )
}
