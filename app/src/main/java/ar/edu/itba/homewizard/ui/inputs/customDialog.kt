package ar.edu.itba.homewizard.ui.inputs


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.homewizard.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        onClick = onClosureRequest,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.onPrimary
                        )
                    ) {
                        Text(dismissText.uppercase(), fontSize = 16.sp)
                    }
                    TextButton(
                        onClick = {if(onSubmit()) onClosureRequest()},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.onPrimary
                        )
                    ) {
                        Text(submitText.uppercase(), fontSize = 16.sp)
                    }
                }
            }
        )
    }
}