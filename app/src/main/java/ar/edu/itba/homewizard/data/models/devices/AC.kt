package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.ui.devices.ac.ACInfo
import ar.edu.itba.homewizard.ui.devices.alarm.AlarmInfo
import ar.edu.itba.homewizard.ui.devices.blinds.BlindInfo
import ar.edu.itba.homewizard.ui.devices.door.DoorInfo
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfo
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfo
import ar.edu.itba.homewizard.ui.devices.refrigerator.RefrigeratorInfo
import ar.edu.itba.homewizard.ui.devices.speaker.SpeakerInfo
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import java.util.HashMap

data class AC (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device {
    var status: Boolean = false
    var temperature: Int = 0
    var mode: String = "fan"
    var verticalSwing = "auto"
    var horizontalSwing: String = "auto"
    var fanSpeed: String = "25"

    companion object {
        val modeNames = arrayOf("heat", "cool", "fan")
        var fanSpeedValues = arrayOf("25", "50", "75", "100")
        var verticalSwingValues = arrayOf("auto", "22", "45", "67", "90")
        var horizontalSwingValues = arrayOf("auto", "-90", "-45", "0", "45", "90")
    }

    init {
        val acState = this.state as AC.ACState
        this.status = acState.status == "on"
        this.temperature = acState.temperature
        this.mode = acState.mode
        this.verticalSwing = acState.verticalSwing
        this.horizontalSwing = acState.horizontalSwing
        this.fanSpeed = acState.fanSpeed
    }

    fun turnOn(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOn", this, listOf()))
    }

    fun turnOff(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOff", this, listOf()))
    }

    fun toggle(devicesViewModel: DevicesViewModel) {
        if (this.status) {
            this.turnOff(devicesViewModel)
        } else {
            this.turnOn(devicesViewModel)
        }
        this.status = !this.status
    }

    fun setTemperature(devicesViewModel: DevicesViewModel, temperature: Int) {
        this.temperature = temperature
        devicesViewModel.executeAction(Action("setTemperature", this, listOf(temperature)))
    }

    fun setMode(devicesViewModel: DevicesViewModel, index: Int) {
        this.mode = modeNames[index]
        devicesViewModel.executeAction(Action("setMode", this, listOf(this.mode)))
    }
    fun setFanSpeed(devicesViewModel: DevicesViewModel, fanSpeed: String) {
        this.fanSpeed = fanSpeed
        devicesViewModel.executeAction(Action("setFanSpeed", this, listOf(fanSpeed)))
    }
    fun setVerticalSwing(devicesViewModel: DevicesViewModel, verticalSwing: String) {
        this.verticalSwing = verticalSwing
        devicesViewModel.executeAction(Action("setVerticalSwing", this, listOf(verticalSwing)))
    }

    fun setHorizontalSwing(devicesViewModel: DevicesViewModel, horizontalSwing: String) {
        this.horizontalSwing = horizontalSwing
        devicesViewModel.executeAction(Action("setHorizontalSwing", this, listOf(horizontalSwing)))
    }

    data class ACState (
        val status: String,
        val temperature: Int,
        val mode: String,
        val verticalSwing: String,
        val horizontalSwing: String,
        val fanSpeed: String
    ) : DeviceState
}