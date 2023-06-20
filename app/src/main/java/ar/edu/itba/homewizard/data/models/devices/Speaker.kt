package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Speaker (
    id: String,
    name: String,
    type: DeviceType,
    state: DeviceState?,
    meta: MetaObject
) : Device(id, name, type, state, meta)
{

    var status: Boolean = false
    var volume: Int = 5
    var genre: String = ""
    var song : SpeakerSong? = SpeakerSong(title = "", artist = "", album = "", duration = "", progress = "")



    init {
        val speakerState = this.state as SpeakerState
        this.status = speakerState.status == "playing"
        this.volume = speakerState.volume
        this.genre = speakerState.genre
        this.song = speakerState.song
    }

    fun prevSong(devicesViewModel: DevicesViewModel, scope: CoroutineScope) {
        devicesViewModel.executeAction(Action("previousSong", this, listOf()))

//        devicesViewModel.updateDevice(id) {
//            this.song = (it as Speaker).song
//            this.song.copy((it as Speaker).song.title, (it as Speaker).song.artist, (it as Speaker).song.album, (it as Speaker).song.duration, (it as Speaker).song.progress)
//            this.songName = (it as Speaker).song.title
//        }
    }

    fun nextSong(devicesViewModel: DevicesViewModel, scope: CoroutineScope) {
        devicesViewModel.executeAction(Action("nextSong", this, listOf()))

//        devicesViewModel.updateDevices(scope, this)


//        devicesViewModel.updateDevice(id) {
//            this.song.copy((it as Speaker).song.title, (it as Speaker).song.artist, (it as Speaker).song.album, (it as Speaker).song.duration, (it as Speaker).song.progress)
//            this.songName = (it as Speaker).song.title
//        }
    }

    fun getPlaylist(devicesViewModel: DevicesViewModel, onSuccess : suspend (List<LinkedTreeMap<String, String>>) -> Unit = {}){
        devicesViewModel.executeActionWithResult(Action("getPlaylist", this, listOf()), onSuccess)
    }

    fun setGenre(devicesViewModel: DevicesViewModel, genre: String) {
        devicesViewModel.executeAction(Action("setGenre", this, listOf(genre)))
        this.genre = genre
    }

    fun setVolume(devicesViewModel: DevicesViewModel, volume: Int){
        devicesViewModel.executeAction(Action("setVolume", this, listOf(volume)))
        this.volume = volume
    }

    fun play(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("play", this, listOf()))
    }

    fun resume(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("resume", this, listOf()))
    }

    fun pause(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("pause", this, listOf()))
    }

    fun toggle(devicesViewModel: DevicesViewModel){
        if (this.status){
            this.pause(devicesViewModel)
        } else {
            this.play(devicesViewModel)
            this.resume(devicesViewModel)
        }

//        this.status = !this.status
    }

        data class SpeakerState (
                val status: String,
                val volume: Int,
                val genre: String,
                var song: SpeakerSong
        ) : DeviceState

        data class SpeakerSong (
                val title: String,
                val artist: String,
                val album: String,
                val duration: String,
                val progress: String
        )
}
