package ar.edu.itba.homewizard.data

import androidx.compose.runtime.Composable
import ar.edu.itba.homewizard.ui.devices.ac.ACInfo
import ar.edu.itba.homewizard.ui.devices.alarm.AlarmInfo
import ar.edu.itba.homewizard.ui.devices.blinds.BlindInfo
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfo
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfo
import ar.edu.itba.homewizard.ui.devices.refrigerator.RefrigeratorInfo
import java.util.Dictionary

class Device(
    val id: String,
    val name: String,
    val type: DeviceType,
    meta: Any,
) {
//    protected abstract val state: Any
}
typealias ComposableFun = @Composable () -> Unit
class DeviceType(var id: String, var name: String){
    companion object {
        val infoCards: HashMap<String, ComposableFun> = hashMapOf(
            "lamp" to { LampInfo() },
            "refrigerator" to { RefrigeratorInfo() },
            "alarm" to { AlarmInfo() },
            "oven" to { OvenInfo() },
            "ac" to { ACInfo() },
            "blinds" to { BlindInfo() }
        )
    }
}