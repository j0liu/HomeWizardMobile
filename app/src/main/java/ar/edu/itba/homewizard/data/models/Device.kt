package ar.edu.itba.homewizard.data.models

import androidx.compose.runtime.Composable
import ar.edu.itba.homewizard.R
import ar.edu.itba.homewizard.data.models.devices.*
import ar.edu.itba.homewizard.ui.devices.door.DoorInfo
import ar.edu.itba.homewizard.ui.devices.ac.ACInfo
import ar.edu.itba.homewizard.ui.devices.alarm.AlarmInfo
import ar.edu.itba.homewizard.ui.devices.blinds.BlindInfo
import ar.edu.itba.homewizard.ui.devices.lamp.LampInfo
import ar.edu.itba.homewizard.ui.devices.oven.OvenInfo
import ar.edu.itba.homewizard.ui.devices.refrigerator.RefrigeratorInfo
import ar.edu.itba.homewizard.ui.devices.speaker.SpeakerInfo
import kotlin.reflect.KClass

open class Device(
     open var id: String,
     open var name: String,
     open var type: DeviceType,
     open var state: DeviceState?,
     open var meta: Any
)

typealias ComposableFun = @Composable () -> Unit
class DeviceType(var id: String, var name: String, val infoScreen: ComposableFun, val icon : Int, val deviceClass : KClass<*>, val stateClass : KClass<*>) {
    companion object {
        val deviceTypes: HashMap<String, DeviceType> = hashMapOf(
            "go46xmbqeomjrsjr" to DeviceType("lamp", "lamp", {LampInfo()}, R.drawable.lightbulb_outline, Lamp::class, Lamp.LampState::class),
            "rnizejqr2di0okho" to DeviceType("fridge", "fridge", { RefrigeratorInfo() }, R.drawable.fridge_outline, Fridge::class, Fridge.FridgeState::class),
            "mxztsyjzsrq7iaqc" to DeviceType("alarm", "alarm", { AlarmInfo() }, R.drawable.alarm_light_outline, Alarm::class, Alarm.AlarmState::class),
            "im77xxyulpegfmv8" to DeviceType("oven", "oven", { OvenInfo() }, R.drawable.toaster_oven , Oven::class, Oven.OvenState::class),
            "li6cbv5sdlatti0j" to DeviceType("ac", "ac", { ACInfo() }, R.drawable.air_conditioner, AC::class, AC.ACState::class),
            "eu0v2xgprrhhg41g" to DeviceType("blinds", "blinds", { BlindInfo() }, R.drawable.blinds, Blind::class, Blind.BlindState::class),
            "lsf78ly0eqrjbz91" to DeviceType("door", "door", { DoorInfo() }, R.drawable.door_closed, Door::class, Door.DoorState::class),
            "c89b94e8581855bc" to DeviceType("speaker", "speaker", {SpeakerInfo()}, R.drawable.volume_high, Speaker::class, Speaker.SpeakerState::class)
        )
    }
}