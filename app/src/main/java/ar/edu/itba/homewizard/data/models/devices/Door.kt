package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Action
import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceState
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel

data class Door (
    override var id: String,
    override var name: String,
    override var type: DeviceType,
    override var state: DeviceState,
    override var meta: Any
) : Device
{
    var status: Boolean = false
    var lock: Boolean = false

    init {
        val doorState = this.state as DoorState
        this.status = doorState.status == "closed"
        this.lock = doorState.lock == "locked"
    }

    fun open(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("open", this, listOf()))
    }

    fun close(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("close", this, listOf()))
    }

    fun lock(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("lock", this, listOf()))
    }

    fun unlock(devicesViewModel: DevicesViewModel){
        devicesViewModel.executeAction(Action("unlock", this, listOf()))
    }

    fun toggleOpenClose(devicesViewModel: DevicesViewModel){
        if (this.status){
            this.open(devicesViewModel)
        } else {
            this.close(devicesViewModel)
        }

        this.status = !this.status
    }

    fun toggleLock(devicesViewModel: DevicesViewModel){
        if (this.lock){
            this.unlock(devicesViewModel)
        } else {
            this.lock(devicesViewModel)
        }

        this.lock = !this.lock
    }

    data class DoorState (
        val status: String,
        val lock: String
    ) : DeviceState


}
