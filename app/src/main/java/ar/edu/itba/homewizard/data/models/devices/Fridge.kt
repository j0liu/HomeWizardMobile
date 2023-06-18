package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

class Fridge (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState?,
    override var meta: Any
) : Device(id, name, type, state, meta) {

    var temperature: Int = 0
    var freezerTemperature: Int = 0
    var mode: String = ""

    init {
        val fridgeState = this.state as FridgeState
        this.temperature = fridgeState.temperature
        this.freezerTemperature = fridgeState.freezerTemperature
        this.mode = fridgeState.mode
    }

    fun changeTemperature(devicesViewModel: DevicesViewModel, temperature: Int){
        devicesViewModel.executeAction(Action("setTemperature", this, listOf(temperature)))
        this.temperature = temperature
    }

    fun changeFreezerTemperature(devicesViewModel: DevicesViewModel, temperature: Int){
        devicesViewModel.executeAction(Action("setFreezerTemperature", this, listOf(temperature)))
        this.freezerTemperature = temperature
    }

    fun setMode(devicesViewModel: DevicesViewModel, mode: Int){
        devicesViewModel.executeAction(Action("setMode", this, listOf(getModeFromInt(mode))))
        this.mode = getModeFromInt(mode)
    }

    fun getMode(): Int {
        when (mode) {
            "default" -> return 0
            "vacation" -> return 1
            "party" -> return 2
        }
        return -1
    }

    fun getModeFromInt(index: Int): String{
        when(index){
            0 -> return "default"
            1 -> return "vacation"
            2 -> return "party"
        }
        return ""
    }

    data class FridgeState (
        val temperature: Int,
        val freezerTemperature: Int,
        val mode: String,
    ) : DeviceState
}
