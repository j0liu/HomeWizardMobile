package ar.edu.itba.homewizard.ui.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.homewizard.R

@Composable
fun PaginationArrows(currentPage: Int, maxPage: Int, modifier: Modifier = Modifier, onLeftClick: () -> Unit, onRightClick: () -> Unit){
    Row(){
        IconButton(
            onClick = { if (currentPage > 0) onLeftClick() },
            modifier = Modifier
                .size(50.dp),
            enabled = currentPage > 0
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.chevron_left) ,
                tint = if(currentPage > 0) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                contentDescription = stringResource(R.string.minus)
            )
        }
        IconButton(
            onClick = { if(currentPage < maxPage) onRightClick() },
            enabled = currentPage < maxPage,
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
                tint = if(currentPage < maxPage) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                contentDescription = stringResource(R.string.plus)
            )
        }
    }

}