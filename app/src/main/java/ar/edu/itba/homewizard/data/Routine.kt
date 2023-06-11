package ar.edu.itba.homewizard.data

class Routine(
    val id: String,
    val name: String,
    val actions: Array<Action>,
    meta: Any
) {

}

class Action(
    val actionName: String,
    val device: Device,
    val params: Array<Any>,
    meta: Any
) {

}
