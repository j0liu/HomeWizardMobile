package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

data class Blind (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device {
    var status : String = "closed"
    var level: Int = 0
    var currentLevel: Int = 0

    init {
        val blindState = this.state as BlindState
        this.level = blindState.level
        this.currentLevel = blindState.currentLevel
        this.status = blindState.status
    }

    fun open(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("open", this, listOf()))
    }

    fun close(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("close", this, listOf()))
    }

    fun setLevel(devicesViewModel: DevicesViewModel, level: Int) {
        this.level = level
        devicesViewModel.executeAction(Action("setLevel", this, listOf(level)))
    }

    data class BlindState (
        val status: String,
        val level: Int,
        val currentLevel: Int
    ) : DeviceState
}