package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun PasswordInput(label : String, text : TextFieldValue, onValueChange : (TextFieldValue) -> Unit, modifier : Modifier = Modifier){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        modifier = Modifier.then(modifier),
        value = text,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        label = { Text(label) },
        visualTransformation =  if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onPrimary
        ),
        trailingIcon = {
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(
                    modifier = Modifier.fillMaxHeight().padding(20.dp),
                    imageVector = ImageVector.vectorResource(id = if(passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                    contentDescription = "content description"
                )
            }
        }
    )
}