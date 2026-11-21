package io.mmaltsev.vkeducation.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mmaltsev.vkeducation.data.appdetails.network.AppApi
import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import javax.inject.Inject

data class AppListUiState(
    val apps: List<AppItem> = emptyList(),
    val isLoading: Boolean = true
)

sealed class AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent()
    data class ShowError(val message: String) : AppListEvent()
}

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppListRepository,
    private val api: AppApi // <-- ДОБАВЛЕНО ДЛЯ ЗАПРОСА РЕЙТИНГА
) : ViewModel() {

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
                _uiState.update { it.copy(isLoading = true) }
                val apps = repository.getAppList()
                _uiState.update { AppListUiState(apps = apps, isLoading = false) }

                // <-- ЗАПУСКАЕМ БЕНЧМАРК ПОСЛЕ ЗАГРУЗКИ СПИСКА
                if (apps.isNotEmpty()) {
                    benchmarkRatings(apps)
                }
            } catch (e: Exception) {
                _events.send(AppListEvent.ShowError("Ошибка: ${e.message}"))
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun benchmarkRatings(currentApps: List<AppItem>) {
        viewModelScope.launch {
            // 1. ПОСЛЕДОВАТЕЛЬНАЯ загрузка (только замер)
            val seqTime = measureTimeMillis {
                for (app in currentApps) {
                    api.getRating(app.id)
                }
            }
            Log.d("RATING_BENCHMARK", "⏱️ Последовательная загрузка: $seqTime мс")

            // 2. ПАРАЛЛЕЛЬНАЯ загрузка (замер + обновление UI)
            val parTime = measureTimeMillis {
                val updatedApps = currentApps.map { app ->
                    async {
                        val rating = api.getRating(app.id)
                        app.copy(rating = rating)
                    }
                }.awaitAll()

                _uiState.update { it.copy(apps = updatedApps) }
            }
            Log.d("RATING_BENCHMARK", "⏱️ Параллельная загрузка: $parTime мс")
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Тулбар нажат!"))
        }
    }
}