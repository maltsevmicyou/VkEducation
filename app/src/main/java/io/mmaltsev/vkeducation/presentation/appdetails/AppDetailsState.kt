package io.mmaltsev.vkeducation.presentation.appdetails

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AppDetailsState {
    data object Error : AppDetailsState
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val descriptionCollapsed: Boolean,
    ) : AppDetailsState
}