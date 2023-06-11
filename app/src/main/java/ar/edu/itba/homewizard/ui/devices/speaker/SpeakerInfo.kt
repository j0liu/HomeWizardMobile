package ar.edu.itba.homewizard.ui.devices.speaker

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.inputs.CustomDropdownMenu
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.ui.theme.Primary
import ar.edu.itba.homewizard.ui.theme.Secondary
import ar.edu.itba.homewizard.ui.theme.lightSurface
import ar.edu.itba.homewizard.viewmodels.SpeakerViewModel
import ar.edu.itba.homewizard.ui.theme.Surface
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun SpeakerInfo(speakerViewModel: SpeakerViewModel = viewModel()) {
    val devicesUiState by speakerViewModel.uiState.collectAsState()
    // list of genres

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Memories",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = lightSurface,
            modifier = Modifier
        )
        Text(
            text = "Maroon 5",
            fontSize = 20.sp,
            color = lightSurface,
            modifier = Modifier
        )
        Text(
            text = "Memories",
            fontSize = 15.sp,
            color = lightSurface,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            LinearProgressIndicator(
                modifier = Modifier.padding(end = 20.dp),
                color = lightSurface,
                progress = devicesUiState.songProgress
            )
            Text(
                text = "1:20/3:20",
                fontSize = 15.sp,
                color = lightSurface,
                modifier = Modifier,

                )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = { speakerViewModel.prev() },
                colors = ButtonDefaults.buttonColors(backgroundColor = lightSurface),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp),
                    imageVector =ImageVector.vectorResource(id = R.drawable.skip_previous),
                    tint = Color.Black,
                    contentDescription = "content description"
                )
            }
            Button(
                onClick = { speakerViewModel.togglePlay() },
                colors = ButtonDefaults.buttonColors(backgroundColor = lightSurface),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp),
                    imageVector =
                    if (devicesUiState.playing) ImageVector.vectorResource(id = R.drawable.play)
                    else ImageVector.vectorResource(id = R.drawable.pause),
                    tint = Color.Black,
                    contentDescription = "content description"
                )
            }
            Button(
                onClick = { speakerViewModel.next() },
                colors = ButtonDefaults.buttonColors(backgroundColor = lightSurface),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp),
                    imageVector =ImageVector.vectorResource(id = R.drawable.skip_next),
                    tint = Color.Black,
                    contentDescription = "content description"
                )
            }
        }
        //todo ver si podemos cambiarlo por eventos de los botones
        CustomSlider(
            value = devicesUiState.volume,
            onValueChange = {  speakerViewModel.setVolume(it) },
            valueRange = 0f..100f,
            onValueChangeFinished = { },
            title = "",
            unit = "",
            icon = R.drawable.volume_high,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        // exposed dropdown menu
        CustomDropdownMenu(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 25.dp),
//                .fillMaxSize(),
            title = "Géneros",
            elements = listOf( "clasica", "country", "dance", "latina", "pop", "rock"),
            onSelected = { speakerViewModel.setGenre(it) },
        )
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            backgroundColor = Surface
        )
        {
            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .height(180.dp)
                    .padding(start = 15.dp)
            ) {
                devicesUiState.playlist.forEach {song ->
                    Text(
                        text = song,
                        fontSize = 12.sp,
                        color = Color.Black,
//                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }
        }
    }

}