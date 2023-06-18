package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun PowerButton(modifier: Modifier = Modifier, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier.then(modifier),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (!selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(20.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(100.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_power_settings_new_24),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = "content description"
        )
    }
}