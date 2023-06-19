package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.*
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

class Blind (
    id: String,
    name: String,
    type: DeviceType,
    state: DeviceState?,
    meta: MetaObject
) : Device(id, name, type, state, meta) {
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