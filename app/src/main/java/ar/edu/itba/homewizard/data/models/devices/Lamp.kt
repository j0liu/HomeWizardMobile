package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

class Lamp (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState?,
    override var meta: Any
) : Device(id, name, type, state, meta)
{
    var status: Boolean = false
    var brightness: Int = 0
    var color: String = ""

    init {
        val lampState = this.state as LampState
        this.status = lampState.status == "on"
        this.brightness = lampState.brightness.toInt()
        this.color = "#FF" + lampState.color.replace("#", "")
    }

    fun turnOn(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOn", this, listOf()))
    }

    fun turnOff(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOff", this, listOf()))
    }

    fun changeColor(devicesViewModel: DevicesViewModel, color: String) {
        devicesViewModel.executeAction(Action("setColor", this, listOf(color.replace("#", "").substring(2))))
        this.color = color
    }

    fun changeBrightness(devicesViewModel: DevicesViewModel, brightness: Int) {
        this.brightness = brightness
        devicesViewModel.executeAction(Action("setBrightness", this, listOf(brightness)))
    }

    fun toggle(devicesViewModel: DevicesViewModel) {
        if (this.status) {
            this.turnOff(devicesViewModel)
        } else {
            this.turnOn(devicesViewModel)
        }
        this.status = !this.status
    }

    data class LampState (
        val status: String,
        val color: String,
        val brightness: Double
    ) : DeviceState
}