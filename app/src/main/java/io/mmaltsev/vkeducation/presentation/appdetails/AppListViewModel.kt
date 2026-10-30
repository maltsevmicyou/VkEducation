package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mmaltsev.vkeducation.data.appdetails.AppListRepositoryImpl
import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

data class AppListUiState(
    val apps: List<AppItem> = emptyList(),
    val isLoading: Boolean = true
)

sealed class AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent()
    data class ShowError(val message: String) : AppListEvent()
}

class AppListViewModel : ViewModel() {

    // Создаем Repository (в реальном проекте это делал бы Hilt/DI)
    private val repository: AppListRepository = AppListRepositoryImpl()

    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState

    private val _events = Channel<AppListEvent>()
    val events = _events.receiveAsFlow()

    init {
        // Загружаем данные при создании ViewModel
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                // Получаем данные через Repository
                val apps = repository.getAppList()

                _uiState.value = AppListUiState(
                    apps = apps,
                    isLoading = false
                )
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