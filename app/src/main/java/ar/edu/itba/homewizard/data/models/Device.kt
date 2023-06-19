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
     var id: String,
     var name: String,
     var type: DeviceType,
     var state: DeviceState?,
     var meta: MetaObject
) {
    var qtyUses : Int
        get() = (meta.qtyUses?:0) as Int
        set(value) {  }

    companion object {
        val orderCriterias: HashMap<String, Comparator<Device>> = hashMapOf(
            "Alphabetical" to compareBy { it.name },
            "Alphabetical Descending" to compareByDescending { it.name },
            "Uses" to compareByDescending { it.qtyUses },
            "Uses Ascending" to compareBy { it.qtyUses },
        )
        val orderCriteriaNames = listOf("Alphabetical", "Alphabetical Descending", "Uses", "Uses Ascending")
    }
}

typealias ComposableFun = @Composable () -> Unit
class DeviceType(var id: String, var name: String, val infoScreen: ComposableFun, val icon : Int, val deviceClass : KClass<*>, val stateClass : KClass<*>) {
    companion object {
        private val LampType = DeviceType("go46xmbqeomjrsjr", "lamp", {LampInfo()}, R.drawable.lightbulb_outline, Lamp::class, Lamp.LampState::class)
        private val FridgeType = DeviceType("rnizejqr2di0okho", "fridge", { RefrigeratorInfo() }, R.drawable.fridge_outline, Fridge::class, Fridge.FridgeState::class)
        private val AlarmType = DeviceType("mxztsyjzsrq7iaqc", "alarm", { AlarmInfo() }, R.drawable.alarm_light_outline, Alarm::class, Alarm.AlarmState::class)
        private val OvenType = DeviceType("im77xxyulpegfmv8", "oven", { OvenInfo() }, R.drawable.toaster_oven , Oven::class, Oven.OvenState::class)
        private val ACType = DeviceType("li6cbv5sdlatti0j", "ac", { ACInfo() }, R.drawable.air_conditioner, AC::class, AC.ACState::class)
        private val BlindsType = DeviceType("eu0v2xgprrhhg41g", "blinds", { BlindInfo() }, R.drawable.blinds, Blind::class, Blind.BlindState::class)
        private val DoorType = DeviceType("lsf78ly0eqrjbz91", "door", { DoorInfo() }, R.drawable.door_closed, Door::class, Door.DoorState::class)
        private val SpeakerType = DeviceType("c89b94e8581855bc", "speaker", {SpeakerInfo()}, R.drawable.volume_high, Speaker::class, Speaker.SpeakerState::class)

        val deviceTypes: HashMap<String, DeviceType> = hashMapOf(
            "go46xmbqeomjrsjr" to LampType,
            "rnizejqr2di0okho" to FridgeType,
            "mxztsyjzsrq7iaqc" to AlarmType,
            "im77xxyulpegfmv8" to OvenType,
            "li6cbv5sdlatti0j" to ACType,
            "eu0v2xgprrhhg41g" to BlindsType,
            "lsf78ly0eqrjbz91" to DoorType,
            "c89b94e8581855bc" to SpeakerType,
        )
        val deviceTypesByName: HashMap<String, DeviceType> = hashMapOf(
            "lamp" to LampType,
            "fridge" to FridgeType,
            "alarm" to AlarmType,
            "oven" to OvenType,
            "ac" to ACType,
            "blinds" to BlindsType,
            "door" to DoorType,
            "speaker" to SpeakerType,
        )
    }
}