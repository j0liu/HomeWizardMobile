package ar.edu.itba.homewizard.ui.inputs


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R

@Composable
fun CustomDialog(openDialog : Boolean, onClosureRequest : () -> Unit, title : String, dismissText: String = stringResource(R.string.cancel), submitText : String, onSubmit : () -> Boolean = {true}, content :@Composable () -> Unit) {

    if (openDialog) {
        AlertDialog(
            onDismissRequest = onClosureRequest,
            title = {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            },
            text = {
                content()
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(
                        onClick = onClosureRequest,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.onPrimary,
                            backgroundColor = MaterialTheme.colors.secondary
                        )
                    ) {
                        Text(dismissText.uppercase(), fontSize = 16.sp)
                    }
                    Button(
                        onClick = {if(onSubmit()) onClosureRequest()},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.onPrimary,
                            backgroundColor = MaterialTheme.colors.secondary

                        )
                    ) {
                        Text(submitText.uppercase(), fontSize = 16.sp)
                    }
                }
            }
        )
    }
}