package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType

class Speaker (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState?,
    override var meta: Any
) : Device(id, name, type, state, meta)
{
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
