package io.mmaltsev.vkeducation.data.appdetails.local

import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AppDetailsEntityMapperTest {

    private val mapper = AppDetailsEntityMapper()

    @Test
    fun `toEntity serializes screenshots as an ordered JSON array preserving duplicates and escaping`() {
        val screenshots = listOf(
            "https://example.test/one?a=1&b=2",
            "quote\" slash\\ newline\n",
            "https://example.test/one?a=1&b=2",
        )

        val entity = mapper.toEntity(appDetails(screenshots))

        assertEquals(json.encodeToString(ListSerializer(String.serializer()), screenshots), entity.screenshots)
    }

    @Test
    fun `toEntity serializes empty screenshot list as empty JSON array`() {
        assertEquals("[]", mapper.toEntity(appDetails(emptyList())).screenshots)
    }

    @Test
    fun `toEntity preserves null screenshot list`() {
        assertNull(mapper.toEntity(appDetails(null)).screenshots)
    }

    @Test
    fun `toDomain deserializes persisted screenshot JSON preserving order and duplicates`() {
        val screenshots = listOf("https://example.test/second", "first\"\\", "https://example.test/second")
        val entity = appDetailsEntity(
            screenshots = json.encodeToString(ListSerializer(String.serializer()), screenshots),
        )

        assertEquals(screenshots, mapper.toDomain(entity).screenshotUrlList)
    }

    @Test
    fun `toDomain maps null and empty JSON screenshots to their empty domain values`() {
        assertNull(mapper.toDomain(appDetailsEntity(screenshots = null)).screenshotUrlList)
        assertEquals(emptyList<String>(), mapper.toDomain(appDetailsEntity(screenshots = "[]")).screenshotUrlList)
    }

    @Test
    fun `toDomain gracefully ignores malformed optional screenshot JSON`() {
        assertNull(mapper.toDomain(appDetailsEntity(screenshots = "[not valid JSON")).screenshotUrlList)
    }

    private fun appDetails(screenshots: List<String>?) = AppDetails(
        id = "app-id",
        name = "App",
        developer = "Developer",
        category = Category.APP,
        ageRating = 0,
        size = 1f,
        iconUrl = "https://example.test/icon.png",
        screenshotUrlList = screenshots,
        description = "Description",
    )

    private fun appDetailsEntity(screenshots: String?) = AppDetailsEntity(
        id = "app-id",
        name = "App",
        developer = "Developer",
        category = Category.APP,
        ageRating = 0,
        size = 1f,
        iconUrl = "https://example.test/icon.png",
        screenshots = screenshots,
        description = "Description",
    )

    private companion object {
        val json = Json
    }
}
