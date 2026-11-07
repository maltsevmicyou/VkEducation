package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository

// Реализация Repository: берет DTO, маппит в Domain, возвращает
class AppListRepositoryImpl : AppListRepository {

    override suspend fun getAppList(): List<AppItem> {
        // Берем хардкод DTO и конвертируем через Mapper
        return fakeAppListDto.map { it.toDomain() }
    }

    // Для витрины
    suspend fun getShowcaseApps(): List<AppItem> {
        return fakeShowcaseDto.map { it.toDomain() }
    }
}