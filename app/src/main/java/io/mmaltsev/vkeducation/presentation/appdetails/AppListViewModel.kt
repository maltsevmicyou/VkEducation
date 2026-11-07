package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel // <-- Добавь
import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject // <-- Добавь

@HiltViewModel // <-- Говорим Hilt, что это ViewModel
class AppListViewModel @Inject constructor( // <-- Hilt сам подставит сюда AppListRepositoryImpl
    private val repository: AppListRepository
) : ViewModel() {

    // УДАЛИ отсюда строку: private val repository: AppListRepository = AppListRepositoryImpl()

    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState

    private val _events = Channel<AppListEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val apps = repository.getAppList()
                _uiState.value = AppListUiState(apps = apps, isLoading = false)
            } catch (e: Exception) {
                _events.send(AppListEvent.ShowError("Ошибка загрузки: ${e.message}"))
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Тулбар нажат! 🚀"))
        }
    }
}