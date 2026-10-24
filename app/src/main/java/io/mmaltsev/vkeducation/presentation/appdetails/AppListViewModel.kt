package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mmaltsev.vkeducation.data.appdetails.hardcodedApps
import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

data class AppListUiState(
    val apps: List<AppItem> = hardcodedApps,
    val isLoading: Boolean = false
)

sealed class AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent()
}

class AppListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState

    private val _events = Channel<AppListEvent>()
    val events = _events.receiveAsFlow()

    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Тулбар нажат! 🚀"))
        }
    }
}