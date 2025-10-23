package io.mmaltsev.vkeducation.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): Flow<AppDetails>
}