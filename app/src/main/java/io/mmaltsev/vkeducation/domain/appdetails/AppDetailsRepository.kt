package io.mmaltsev.vkeducation.domain.appdetails

interface AppDetailsRepository {
    suspend fun get(): AppDetails
}