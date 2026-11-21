package io.mmaltsev.vkeducation.domain.appdetails

interface AppListRepository {
    suspend fun getAppList(): List<AppItem>
}