package ar.edu.itba.homewizard.data

import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.ui.devices.door.DoorInfo
import ar.edu.itba.homewizard.ui.devices.ac.ACInfo
import ar.edu.itba.homewizard.ui.devices.alarm.AlarmInfo
import ar.edu.itba.homewizard.ui.devices.blinds.BlindInfo
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfo
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfo
import ar.edu.itba.homewizard.ui.devices.refrigerator.RefrigeratorInfo
import ar.edu.itba.homewizard.ui.devices.speaker.SpeakerInfo

class Device(
    val id: String,
    val name: String,
    val type: DeviceType,
    meta: Any,
) {
//    protected abstract val state: Any
}
typealias ComposableFun = @Composable () -> Unit
class DeviceType(var id: String, var name: String, val infoScreen: ComposableFun, val icon : Int) {
    companion object {
        val deviceTypes: HashMap<String, DeviceType> = hashMapOf(
            // TODO: Sacar esto de aca
            "lamp" to DeviceType("lamp", "lamp", {LampInfo()}, R.drawable.lightbulb_outline),
            "fridge" to DeviceType("fridge", "fridge", { RefrigeratorInfo() }, R.drawable.fridge_outline),
            "alarm" to DeviceType("alarm", "alarm", { AlarmInfo() }, R.drawable.alarm_light_outline),
            "oven" to DeviceType("oven", "oven", { OvenInfo() }, R.drawable.toaster_oven),
            "ac" to DeviceType("ac", "ac", { ACInfo() }, R.drawable.air_conditioner),
            "blinds" to DeviceType("blinds", "blinds", { BlindInfo() }, R.drawable.blinds ),
            "door" to DeviceType("door", "door", { DoorInfo() }, R.drawable.door_closed),
            "speaker" to DeviceType("speaker", "speaker", {SpeakerInfo()}, R.drawable.volume_high),

            "go46xmbqeomjrsjr" to DeviceType("lamp", "lamp", {LampInfo()}, R.drawable.lightbulb_outline),
            "rnizejqr2di0okho" to DeviceType("fridge", "fridge", { RefrigeratorInfo() }, R.drawable.fridge_outline),
            "mxztsyjzsrq7iaqc" to DeviceType("alarm", "alarm", { AlarmInfo() }, R.drawable.alarm_light_outline),
            "im77xxyulpegfmv8" to DeviceType("oven", "oven", { OvenInfo() }, R.drawable.toaster_oven),
            "li6cbv5sdlatti0j" to DeviceType("ac", "ac", { ACInfo() }, R.drawable.air_conditioner),
            "eu0v2xgprrhhg41g" to DeviceType("blinds", "blinds", { BlindInfo() }, R.drawable.blinds ),
            "lsf78ly0eqrjbz91" to DeviceType("door", "door", { DoorInfo() }, R.drawable.door_closed),
            "c89b94e8581855bc" to DeviceType("speaker", "speaker", {SpeakerInfo()}, R.drawable.volume_high)
        )
    }
}