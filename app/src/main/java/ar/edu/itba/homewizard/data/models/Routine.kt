package ar.edu.itba.homewizard.data.models

class Routine(
    val id: String,
    val name: String,
    val actions: List<Action>,
    val meta: Any
) {
}

class Action(
    val actionName: String,
    val device: Device,
    val params: List<Any>
) {

}
