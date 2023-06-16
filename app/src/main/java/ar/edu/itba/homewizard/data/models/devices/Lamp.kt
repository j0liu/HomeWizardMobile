package ar.edu.itba.homewizard.data.models.devices

import ar.edu.itba.homewizard.data.models.Device
import ar.edu.itba.homewizard.data.models.DeviceType
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import javax.inject.Inject

class Lamp (
    id: String,
    name: String,
    type: DeviceType,
    state: Any?,
    meta: Any
) : Device(id, name, type, state, meta)
{
    fun turnOn(devicesViewModel: DevicesViewModel) {
        devicesViewModel.printHola("lamp!")
    }

}