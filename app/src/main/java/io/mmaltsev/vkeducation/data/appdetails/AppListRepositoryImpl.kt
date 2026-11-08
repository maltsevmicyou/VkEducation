package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.data.appdetails.network.AppApi
import io.mmaltsev.vkeducation.data.appdetails.network.toDomain
import io.mmaltsev.vkeducation.domain.appdetails.AppItem
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val api: AppApi // Hilt сам предоставит этот интерфейс
) : AppListRepository {

    override suspend fun getAppList(): List<AppItem> {
        // Делаем запрос к сети и маппим результат
        return api.getCatalog().map { it.toDomain() }
    }
}