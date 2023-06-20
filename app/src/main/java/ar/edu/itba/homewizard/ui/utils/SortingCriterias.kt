package ar.edu.itba.homewizard.ui.utils

import androidx.compose.ui.unit.dp

class SortingCriterias {
    companion object {
        const val BY_NAME = "name"
        const val BY_NAME_DESC = "name_descending"
        const val BY_USAGE = "usage"
        const val BY_USAGE_ASC = "usage_ascending"
        val sortingCriteriaNames = listOf(BY_NAME, BY_NAME_DESC, BY_USAGE, BY_USAGE_ASC)
    }
}