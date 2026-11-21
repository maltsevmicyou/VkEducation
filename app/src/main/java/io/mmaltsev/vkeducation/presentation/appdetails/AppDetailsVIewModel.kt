package io.mmaltsev.vkeducation.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mmaltsev.vkeducation.domain.appdetails.GetAppDetailsUseCase
import io.mmaltsev.vkeducation.domain.appdetails.GetSimilarAppsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val getSimilarAppsUseCase: GetSimilarAppsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var loadingJob: Job? = null
    private var installJob: Job? = null // <-- ДОБАВЛЕНО

    init {
        getAppDetails()
    }

    // <-- ЗАМЕНА showUnderDevelopmentMessage НА onInstallClick
    fun onInstallClick() {
        val current = _state.value as? AppDetailsState.Content ?: return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
            _state.update { current.copy(installState = InstallState.NotInstalled) }
        } else {
            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 0..100 step 5) {
            delay(150)
            _state.update { currentState ->
                (currentState as? AppDetailsState.Content)?.copy(
                    installState = InstallState.Installing(progress)
                ) ?: currentState
            }
        }
        _state.update { currentState ->
            (currentState as? AppDetailsState.Content)?.copy(
                installState = InstallState.Installed
            ) ?: currentState
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }

    fun getAppDetails() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            _state.value = AppDetailsState.Loading

            try {
                val similarApps = getSimilarAppsUseCase()
                getAppDetailsUseCase("fa2e31b8-1234-4cf7-9914-108a170a1b01").collect { appDetails ->
                    val currentState = _state.value
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        similarApps = similarApps,
                        descriptionCollapsed = (currentState as? AppDetailsState.Content)?.descriptionCollapsed ?: false,
                        installState = InstallState.NotInstalled // <-- ЯВНО УКАЗЫВАЕМ
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
                Log.e("AppDetailsViewModel", "Failed to load app details", e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loadingJob?.cancel()
        installJob?.cancel() // <-- ОТМЕНЯЕМ ПРИ УНИЧТОЖЕНИИ
    }
}