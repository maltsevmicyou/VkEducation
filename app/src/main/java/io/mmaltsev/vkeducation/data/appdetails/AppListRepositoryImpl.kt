package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository
import javax.inject.Inject // <-- Добавь этот импорт

class AppListRepositoryImpl @Inject constructor() : AppListRepository {

    override suspend fun getAppList(): List<AppItem> {
        return fakeAppListDto.map { it.toDomain() }
    }

    suspend fun getShowcaseApps(): List<AppItem> {
        return fakeShowcaseDto.map { it.toDomain() }
    }
}