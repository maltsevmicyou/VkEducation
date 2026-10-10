package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsDao
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.AppDetailsRepository
import java.util.concurrent.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): Flow<AppDetails> = flow {
        var screenshotRefreshAttempted = false
        emitAll(
            dao.getAppDetails(id).map { entity ->
                when {
                    entity == null -> {
                        screenshotRefreshAttempted = true
                        fetchAndCache(id)
                    }
                    entity.screenshots == null && !screenshotRefreshAttempted -> {
                        screenshotRefreshAttempted = true
                        try {
                            fetchAndCache(id)
                        } catch (cancellation: CancellationException) {
                            throw cancellation
                        } catch (_: Exception) {
                            entityMapper.toDomain(entity)
                        }
                    }
                    else -> entityMapper.toDomain(entity)
                }
            }
        )
    }

    private suspend fun fetchAndCache(id: String): AppDetails {
        val dto = appApi.getAppDetails(id)
        val domain = mapper.toDomain(dto)
        val entity = entityMapper.toEntity(domain)
        withContext(Dispatchers.IO) {
            dao.insertAppDetails(entity)
        }
        return domain
    }
}
