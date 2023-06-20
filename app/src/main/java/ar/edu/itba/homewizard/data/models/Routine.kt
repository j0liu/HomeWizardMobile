package ar.edu.itba.homewizard.data.models

import ar.edu.itba.homewizard.ui.utils.SortingCriterias

class Routine(
    val id: String,
    val name: String,
    val actions: List<Action>,
    val meta: MetaObject
) {
    var qtyUses : Int
        get() = meta.qtyUses
        set(value) { meta.qtyUses = value }
    companion object {
        val orderCriterias: HashMap<String, Comparator<Routine>> = hashMapOf(
            SortingCriterias.BY_NAME to compareBy(String.CASE_INSENSITIVE_ORDER) { it.name },
            SortingCriterias.BY_NAME_DESC to compareByDescending(String.CASE_INSENSITIVE_ORDER) { it.name },
            SortingCriterias.BY_USAGE to compareByDescending { it.qtyUses },
            SortingCriterias.BY_USAGE_ASC to compareBy { it.qtyUses },
        )
    }
}

