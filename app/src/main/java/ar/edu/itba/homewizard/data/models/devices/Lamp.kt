package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import javax.inject.Inject

data class Lamp (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device
{
    var status: Boolean = false
    var brightness: Int = 0
    var color: String = ""

    init {
        val lampState = this.state as LampState
        this.status = lampState.status == "on"
        this.brightness = lampState.brightness.toInt()
        this.color = lampState.color
    }

    fun turnOn(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOn", this, listOf()))
    }

    fun turnOff(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOff", this, listOf()))
    }

    fun changeColor(devicesViewModel: DevicesViewModel, color: String) {
        devicesViewModel.executeAction(Action("changeColor", this, listOf(color)))
    }

    fun changeBrightness(devicesViewModel: DevicesViewModel, brightness: Int) {
        devicesViewModel.executeAction(Action("changeBrightness", this, listOf(brightness)))
    }

    fun toggle(devicesViewModel: DevicesViewModel) {
//        this.status = !this.status
        if (this.status) {
            this.turnOff(devicesViewModel)
        } else {
            this.turnOn(devicesViewModel)
        }
    }

    data class LampState (
        val status: String,
        val color: String,
        val brightness: Double
    ) : DeviceState
}