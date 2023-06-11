package ar.edu.itba.homewizard.ui.devices.speaker

data class SpeakerUiState (
    val playing : Boolean = false,
    val songProgress : Float = 0.75f,
    val volume : Float = 50f,
    val genre : String = "Rock",
    val playlist: List<String> = listOf("Song 1", "Song 2", "Song 3", "Song 4", "Song 5")
)