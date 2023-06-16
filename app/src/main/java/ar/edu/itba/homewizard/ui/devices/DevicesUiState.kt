package ar.edu.itba.homewizard.ui.devices

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.models.Device

data class DevicesUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    var devices: List<Device> = listOf(),
    var currentDevice: Device? = null,

    // UI
    var isLoading: Boolean = false,
    var scaffoldState: BottomSheetScaffoldState = BottomSheetScaffoldState(
        DrawerState(DrawerValue.Closed),
        BottomSheetState(BottomSheetValue.Collapsed),
        SnackbarHostState()
    ),
    var overflowExpanded: Boolean = false
)