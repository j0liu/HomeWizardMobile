package ar.edu.itba.homewizard.data.models

class Routine(
    val id: String,
    val name: String,
    val actions: List<Action>,
    val meta: MetaObject
) {
    var qtyUses : Int
        get() = meta.qtyUses
        set(value) {  }
    companion object {
        val orderCriterias: HashMap<String, Comparator<Routine>> = hashMapOf(
            "Alphabetical" to compareBy { it.name },
            "Alphabetical Descending" to compareByDescending { it.name },
            "Uses" to compareByDescending { it.qtyUses },
            "Uses Ascending" to compareBy { it.qtyUses },
        )
        val orderCriteriaNames = listOf("Alphabetical", "Alphabetical Descending", "Uses", "Uses Ascending")
    }
}

