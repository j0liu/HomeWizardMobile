package ar.edu.itba.homewizard.data

import java.util.Dictionary

class Device(
    val id: String,
    val name: String,
    val type: DeviceType,
    meta: Any,
) {
//    protected abstract val state: Any
}


class DeviceType(var id: String, var name: String){}