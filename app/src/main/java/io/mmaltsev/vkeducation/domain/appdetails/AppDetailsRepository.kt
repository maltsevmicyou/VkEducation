package io.mmaltsev.vkeducation.domain.appdetails

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails
}