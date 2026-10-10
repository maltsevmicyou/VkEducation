package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsDao
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntity
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import java.io.IOException
import java.util.concurrent.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDetailsRepositoryImplTest {

    @Test
    fun `cached row without screenshots is refreshed and updated in Room`() = runBlocking {
        val old = entity(screenshots = null, name = "Old cached name")
        val expectedUrls = listOf("https://example.test/one.png", "https://example.test/two.png")
        val dao = FakeDao(old)
        var apiCalls = 0
        val repository = repository(dao) {
            apiCalls++
            dto(screenshots = expectedUrls, name = "Fresh API name")
        }

        val emissions = repository.getAppDetails(APP_ID).toList()

        assertEquals(1, apiCalls)
        assertEquals(listOf("Fresh API name", "Fresh API name"), emissions.map(AppDetails::name))
        assertEquals(expectedUrls, emissions.last().screenshotUrlList)
        assertEquals(expectedUrls, entityMapper.toDomain(dao.current!!).screenshotUrlList)
    }

    @Test
    fun `cold cache response without screenshots is not immediately fetched again`() = runBlocking {
        val dao = FakeDao(initial = null)
        var apiCalls = 0
        val repository = repository(dao) {
            apiCalls++
            dto(screenshots = null)
        }

        repository.getAppDetails(APP_ID).toList()

        assertEquals(1, apiCalls)
    }

    @Test
    fun `cached empty JSON array is considered refreshed cache and skips API`() = runBlocking {
        val dao = FakeDao(entity(screenshots = "[]"))
        var apiCalls = 0
        val repository = repository(dao) {
            apiCalls++
            error("API should not be called for a non-null persisted screenshot list")
        }

        val emission = repository.getAppDetails(APP_ID).first()

        assertEquals(emptyList<String>(), emission.screenshotUrlList)
        assertEquals(0, apiCalls)
    }

    @Test
    fun `cached row remains available when screenshot backfill fails`() = runBlocking {
        val old = entity(screenshots = null, name = "Offline cached name")
        val dao = FakeDao(old)
        var apiCalls = 0
        val repository = repository(dao) {
            apiCalls++
            throw IOException("offline")
        }

        val emissions = repository.getAppDetails(APP_ID).toList()

        assertEquals(1, apiCalls)
        assertEquals(listOf("Offline cached name", "Offline cached name"), emissions.map(AppDetails::name))
    }

    @Test
    fun `successful backfill with no screenshot list is attempted only once per collection`() = runBlocking {
        val dao = FakeDao(entity(screenshots = null))
        var apiCalls = 0
        val repository = repository(dao) {
            apiCalls++
            dto(screenshots = null)
        }

        repository.getAppDetails(APP_ID).toList()

        assertEquals(1, apiCalls)
    }

    @Test
    fun `API error still propagates when there is no cached row`() = runBlocking {
        val dao = FakeDao(initial = null)
        val repository = repository(dao) { throw IOException("offline") }

        val failure = captureFailure {
            repository.getAppDetails(APP_ID).toList()
        }

        assertEquals(IOException::class.java, failure::class.java)
    }

    @Test
    fun `cancellation during backfill propagates`() = runBlocking {
        val dao = FakeDao(entity(screenshots = null))
        val repository = repository(dao) { throw CancellationException("cancelled") }

        val failure = captureFailure {
            repository.getAppDetails(APP_ID).toList()
        }

        assertEquals(CancellationException::class.java, failure::class.java)
    }

    private fun repository(dao: FakeDao, apiCall: suspend () -> AppDetailsDto) = AppDetailsRepositoryImpl(
        appApi = object : AppApi {
            override suspend fun getAppDetails(id: String): AppDetailsDto = apiCall()
        },
        dao = dao,
        mapper = AppDetailsMapper(),
        entityMapper = entityMapper,
    )

    private fun dto(screenshots: List<String>?, name: String = "API app") = AppDetailsDto(
        id = APP_ID,
        name = name,
        developer = "Developer",
        category = Category.APP,
        ageRating = 0,
        size = 1.0,
        icon = "https://example.test/icon.png",
        screenshots = screenshots,
        description = "Description",
    )

    private fun entity(screenshots: String?, name: String = "Cached app") = AppDetailsEntity(
        id = APP_ID,
        name = name,
        developer = "Developer",
        category = Category.APP,
        ageRating = 0,
        size = 1f,
        iconUrl = "https://example.test/icon.png",
        screenshots = screenshots,
        description = "Description",
    )

    private suspend fun captureFailure(block: suspend () -> Unit): Throwable = try {
        block()
        error("Expected an exception")
    } catch (failure: Throwable) {
        failure
    }

    private class FakeDao(initial: AppDetailsEntity?) : AppDetailsDao {
        var current: AppDetailsEntity? = initial
            private set

        override fun getAppDetails(id: String): Flow<AppDetailsEntity?> = flow {
            emit(current)
            emit(current)
        }

        override fun insertAppDetails(appDetails: AppDetailsEntity) {
            current = appDetails
        }
    }

    private companion object {
        const val APP_ID = "app-id"
        val entityMapper = AppDetailsEntityMapper()
    }
}
