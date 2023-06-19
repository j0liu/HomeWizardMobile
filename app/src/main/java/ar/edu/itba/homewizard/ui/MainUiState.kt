package ar.edu.itba.homewizard.ui

import androidx.compose.material.*

data class MainUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    val isLoading: Boolean = false,
    var displayBottomBar : Boolean = true,
    val collapseBottomSheet: () -> Unit,
    val afterCollapseBottomSheet: () -> Unit = {},
    var scaffoldState: BottomSheetScaffoldState = BottomSheetScaffoldState(
        DrawerState(DrawerValue.Closed),
        BottomSheetState(BottomSheetValue.Collapsed, confirmValueChange = {
            if (it.equals(BottomSheetValue.Collapsed))
                collapseBottomSheet()
            true
        }),
        SnackbarHostState()
    ),
)