package ar.edu.itba.homewizard.data.models.devices
import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

class Oven (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState?,
    override var meta: Any
) : Device(id, name, type, state, meta)
{
    var status: Boolean = false
    var temperature: Int = 90
    var heat: String = "conventional"
    var grill = "large"
    var convection: String = "normal"

    companion object {
        val heatModes = arrayOf("conventional", "bottom", "top")
        var grillModes = arrayOf("large", "eco", "off")
        var convectionModes = arrayOf("normal", "eco", "off")
    }

    init {
        val ovenState = this.state as OvenState
        this.status = ovenState.status == "on"
        this.temperature = ovenState.temperature
        this.heat = ovenState.heat
        this.grill = ovenState.grill
        this.convection = ovenState.convection
    }

    fun turnOn(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOn", this, listOf()))
    }

    fun turnOff(devicesViewModel: DevicesViewModel) {
        devicesViewModel.executeAction(Action("turnOff", this, listOf()))
    }

    fun setTemperature(devicesViewModel: DevicesViewModel, temperature: Int) {
        this.temperature = temperature
        devicesViewModel.executeAction(Action("setTemperature", this, listOf(temperature)))
    }
    fun setHeat(devicesViewModel: DevicesViewModel, heat: String) {
        this.heat = heat
        devicesViewModel.executeAction(Action("setHeat", this, listOf(heat)))
    }
    fun setGrill(devicesViewModel: DevicesViewModel, grill: String) {
        this.grill = grill
        devicesViewModel.executeAction(Action("setGrill", this, listOf(grill)))
    }
    fun setConvection(devicesViewModel: DevicesViewModel, convection: String) {
        this.convection = convection
        devicesViewModel.executeAction(Action("setConvection", this, listOf(convection)))
    }

    fun toggle(devicesViewModel: DevicesViewModel) {
        if (this.status) {
            this.turnOff(devicesViewModel)
        } else {
            this.turnOn(devicesViewModel)
        }
        this.status = !this.status
    }
    data class OvenState (
        val status: String,
        val temperature: Int,
        val heat: String,
        val grill: String,
        val convection: String
    ) : DeviceState
}



