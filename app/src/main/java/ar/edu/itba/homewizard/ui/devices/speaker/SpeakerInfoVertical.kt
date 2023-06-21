package ar.edu.itba.homewizard.ui.devices.speaker


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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


@SuppressLint("DiscouragedApi")
@Composable
fun SpeakerInfoVertical(devicesViewModel: DevicesViewModel, speaker: Speaker, multiplier : Float = 1f) {
//    val devicesUiState by speakerViewModel.uiState.collectAsState()
    // list of genres
    val scope  = rememberCoroutineScope()
    val mContext = LocalContext.current
    var songList by remember { mutableStateOf(listOf<LinkedTreeMap<String, String>>())}
    val genres = listOf("pop", "classical", "country", "dance", "latina", "rock")

    fun getTime(time : String) : Int {
        val MinSec = time.split(":")
        if (MinSec.size == 2) {
            return MinSec[1].toInt() + (MinSec[0].toInt() * 60)
        }
        return MinSec[1].toInt()
    }

    LaunchedEffect(Unit) {
        while(true) {
            speaker.getPlaylist(devicesViewModel) {
                    list -> songList = list
            }
            devicesViewModel.updateDevice(speaker.id, scope)
            delay(800)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = if (speaker.song?.title != null) speaker.song!!.title else stringResource(R.string.NoSong),
            fontSize = (multiplier*20).sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text =  if (speaker.song?.artist != null) speaker.song!!.artist else "",
            fontSize = (multiplier*15).sp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = if (speaker.song?.album != null) speaker.song!!.album else "",
            fontSize = (multiplier * 10).sp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(bottom = 15.dp*multiplier),
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
            if (speaker.song != null) {
                LinearProgressIndicator(
                    modifier = Modifier.padding(end = 20.dp*multiplier).height((multiplier * 4).dp).fillMaxWidth(0.7f),
                    color = MaterialTheme.colors.surface,
                    progress = getTime(speaker.song!!.progress) / getTime(speaker.song!!.duration).toFloat(),
                )
                Text(
                    text = "${speaker.song!!.progress}/${speaker.song!!.duration}",
                    fontSize = (multiplier * 15).sp,
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp*multiplier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = { speaker.prevSong(devicesViewModel, scope) },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(20.dp*multiplier)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp*multiplier),
                    imageVector =ImageVector.vectorResource(id = R.drawable.skip_previous),
                    tint = Color.Black,
                    contentDescription = "content description"
                )
            }
            Button(
                onClick = { speaker.toggle(devicesViewModel) },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(20.dp*multiplier)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp*multiplier),
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
                        .size(60.dp*multiplier),
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
                .padding(horizontal = 20.dp),
            multiplier = multiplier
        )
        // exposed dropdown menu

        CustomDropdownMenu(
            selected = speaker.genre,
            modifier = Modifier
                .padding(horizontal = 10.dp*multiplier, vertical = 25.dp*multiplier),
            title = stringResource(R.string.genres),
            multiplier = multiplier,
            elements = genres,
            onSelected = {
                speaker.setGenre(devicesViewModel, it)
                speaker.getPlaylist(devicesViewModel) {
                        list -> songList = list
                }
            },
        )
        if (songList.isNotEmpty() && speaker.song != null) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp*multiplier),
                backgroundColor = MaterialTheme.colors.surface
            )
            {
                Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .height(180.dp*multiplier)
                        .padding(start = 15.dp*multiplier)
                ) {


                    songList.forEach() { song ->
                        Text(
                            song.get("title")!!,
                            fontSize = (multiplier * 15).sp,
                            fontWeight = if (speaker.song!!.title == song.get("title")!!) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }

}