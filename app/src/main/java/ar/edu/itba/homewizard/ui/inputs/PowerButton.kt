package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun PowerButton(modifier: Modifier = Modifier, selected: Boolean, multiplier: Float = 1f, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier.then(modifier)
            .size(120.dp * multiplier),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (!selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(20.dp * multiplier),
    ) {
        Icon(
            modifier = Modifier
                .size(100.dp * multiplier),
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_power_settings_new_24),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = "content description"
        )
    }
}