package ar.edu.itba.homewizard.data.models

class Routine(
    val id: String,
    val name: String,
    val actions: List<Action>,
    val meta: Any
) {
}

