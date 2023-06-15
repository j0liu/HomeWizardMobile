package ar.edu.itba.homewizard.ui.devices

import androidx.compose.material.*
import ar.edu.itba.homewizard.data.Device

data class DevicesUiState @OptIn(ExperimentalMaterialApi::class) constructor(
    var devices: MutableSet<Device> = mutableSetOf(),
    var currentDevice: Device? = null,
    var isLoading: Boolean = false,
    var scaffoldState: BottomSheetScaffoldState = BottomSheetScaffoldState(
        DrawerState(DrawerValue.Closed),
        BottomSheetState(BottomSheetValue.Collapsed),
        SnackbarHostState()
    ),
    var overflowExpanded: Boolean = false
)