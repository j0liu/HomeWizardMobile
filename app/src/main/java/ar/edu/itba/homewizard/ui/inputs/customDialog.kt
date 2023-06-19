package ar.edu.itba.homewizard.ui.inputs


import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun CustomDialog(openDialog : Boolean, onClosureRequest : () -> Unit, title : String, dismissText: String = stringResource(R.string.cancel), submitText : String, onSubmit : () -> Boolean = {true}, content :@Composable () -> Unit) {

    if (openDialog) {
        AlertDialog(
            onDismissRequest = onClosureRequest,
            title = {
                Text(text = title)
            },
            text = {
               content()
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier,
                        onClick = onClosureRequest
                    ) {
                        Text(dismissText)
                    }
                    Button(onClick = {if(onSubmit()) onClosureRequest()}
                    ) {
                        Text(submitText)
                    }
                }
            }
        )
    }
}