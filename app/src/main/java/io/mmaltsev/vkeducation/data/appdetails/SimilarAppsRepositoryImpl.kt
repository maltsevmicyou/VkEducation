package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.domain.appdetails.SimilarApp
import io.mmaltsev.vkeducation.domain.appdetails.SimilarAppsRepository
import javax.inject.Inject

class SimilarAppsRepositoryImpl @Inject constructor(
    private val api: SimilarAppsApi,
    private val mapper: SimilarAppMapper,
) : SimilarAppsRepository {
    override suspend fun getSimilarApps(): List<SimilarApp> =
        api.getSimilarApps().map(mapper::toDomain)
}
