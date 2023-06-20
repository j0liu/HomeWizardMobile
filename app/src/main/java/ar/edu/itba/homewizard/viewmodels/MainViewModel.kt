package ar.edu.itba.homewizard.viewmodels

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.homewizard.bridges.SnackbarBridge
import ar.edu.itba.homewizard.bridges.SnackbarType
import ar.edu.itba.homewizard.ui.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(snackbarBridge: SnackbarBridge) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(collapseBottomSheet = { collapseBottomSheet() }))
    var uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        snackbarBridge.subscribe { message, type ->
            println("Message received: $message")
            viewModelScope.launch {
                val duration = when (type) {
                    SnackbarType.INFO -> SnackbarDuration.Short
                    SnackbarType.ERROR -> SnackbarDuration.Long
                    SnackbarType.PANIC -> SnackbarDuration.Indefinite
                }
                val buttonText = if (type == SnackbarType.PANIC) null else "OK"
                _uiState.value.scaffoldState.snackbarHostState.showSnackbar(
                    message,
                    buttonText,
                    duration,
                )
            }
        }
    }

    fun collapseBottomSheet(scope: CoroutineScope? = null) {
        scope?.launch {
            _uiState.value.scaffoldState.bottomSheetState.collapse()
        }
        _uiState.update {
            it.copy(
                displayBottomBar = true
            )
        }
        _uiState.value.afterCollapseBottomSheet()
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun setBackHandler(scope: CoroutineScope) {
        return BackHandler(enabled = uiState.value.scaffoldState.bottomSheetState.isExpanded) {
            collapseBottomSheet(scope)
        }
    }

    fun setAfterCollapseBottomSheetAction(function: () -> Unit) {
        _uiState.update {
            it.copy(
                afterCollapseBottomSheet = function
            )
        }
    }

    fun setBottomBarVisibility(visible: Boolean) {
        _uiState.update {
            it.copy(
                displayBottomBar = visible
            )
        }
        println("New displayBottomBar: ${_uiState.value.displayBottomBar}")
    }
}