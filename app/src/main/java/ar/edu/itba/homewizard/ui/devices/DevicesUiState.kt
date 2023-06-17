package ar.edu.itba.homewizard.ui.devices

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.models.Device

data class DevicesUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    var devices: List<Device> = listOf(),
    val currentDevice: Device? = null,

    val collapseBottomSheet: () -> Unit,
    // UI
    var isLoading: Boolean = false,
    var scaffoldState: BottomSheetScaffoldState = BottomSheetScaffoldState(
        DrawerState(DrawerValue.Closed),
        BottomSheetState(BottomSheetValue.Collapsed, confirmValueChange = {
            if (it.equals(BottomSheetValue.Collapsed))
                collapseBottomSheet()
            true
        }),
        SnackbarHostState()
    ),
    var overflowExpanded: Boolean = false
)