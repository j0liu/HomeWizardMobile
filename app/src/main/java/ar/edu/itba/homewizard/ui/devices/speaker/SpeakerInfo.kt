package ar.edu.itba.homewizard.ui.devices.speaker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.Speaker
import ar.edu.itba.homewizard.ui.inputs.CustomDropdownMenu
import ar.edu.itba.homewizard.ui.inputs.CustomSlider
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import ar.edu.itba.homewizard.viewmodels.SpeakerViewModel
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.delay


@Composable
fun SpeakerInfo(devicesViewModel: DevicesViewModel = hiltViewModel()) {
//    val devicesUiState by speakerViewModel.uiState.collectAsState()
    // list of genres
    val scope  = rememberCoroutineScope()
    val devicesUiState by devicesViewModel.uiState.collectAsState()
    val speaker = devicesUiState.currentDevice as Speaker

    var songList by remember { mutableStateOf(listOf<LinkedTreeMap<String, String>>())}

    fun getTime(time : String) : Int {
        val MinSec = time.split(":")
        if (MinSec.size == 2) {
            return MinSec[1].toInt() + (MinSec[0].toInt() * 60)
        }
        return MinSec[1].toInt()
    }

    speaker.getPlaylist(devicesViewModel) {
            list -> songList = list
    }
    LaunchedEffect(Unit) {
        while(true) {
            devicesViewModel.updateDevice(speaker.id, scope)
            delay(800)
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = speaker.song.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = speaker.song.artist,
            fontSize = 15.sp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = speaker.song.album,
            fontSize = 10.sp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(bottom = 15.dp),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            LinearProgressIndicator(
                modifier = Modifier.padding(end = 20.dp),
                color = MaterialTheme.colors.surface,
                progress = getTime(speaker.song.progress) / getTime(speaker.song.duration).toFloat()
            )
            Text(
                text = "${speaker.song.progress}/${speaker.song.duration}",
                fontSize = 15.sp,
                color = MaterialTheme.colors.surface,
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
                onClick = { speaker.prevSong(devicesViewModel, scope) },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
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
                onClick = { speaker.toggle(devicesViewModel) },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp),
                    imageVector =
                    if (speaker.status) ImageVector.vectorResource(id = R.drawable.pause)
                    else ImageVector.vectorResource(id = R.drawable.play),
                    tint = Color.Black,
                    contentDescription = "content description"
                )
            }
            Button(
                onClick = { speaker.nextSong(devicesViewModel, scope) },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
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
            value = speaker.volume.toFloat(),
//            onValueChange = {  speakerViewModel.setVolume(it) },
            valueRange = 0f..10f,
            onValueChangeFinished = {speaker.setVolume(devicesViewModel, it.toInt()) },
            title = "",
            unit = "",
            icon = R.drawable.volume_high,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        // exposed dropdown menu
        CustomDropdownMenu(
            selected = speaker.genre,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 25.dp),
//                .fillMaxSize(),
            title = "Géneros",
            elements = listOf( "classical", "country", "dance", "latina", "pop", "rock"),
            onSelected = {
                speaker.setGenre(devicesViewModel, it)
                speaker.getPlaylist(devicesViewModel) {
                    list -> songList = list
                }
            },
        )
        if (!songList.isEmpty()) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            backgroundColor = MaterialTheme.colors.surface
        )
        {
            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .height(180.dp)
                    .padding(start = 15.dp)
            ) {


                songList.forEach() { song ->
                    Text(
                        song.get("title")!!,
                        fontWeight = if (speaker.song.title == song.get("title")!!) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
            }
        }
    }

}