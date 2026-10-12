package io.mmaltsev.vkeducation.domain.appdetails

interface SimilarAppsRepository {
    suspend fun getSimilarApps(): List<SimilarApp>
}
