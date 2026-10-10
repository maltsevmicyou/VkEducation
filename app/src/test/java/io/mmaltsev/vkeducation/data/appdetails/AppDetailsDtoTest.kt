package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDetailsDtoTest {

    @Test
    fun `actual catalog screenshotUrlList survives API decoding and Room round trip`() {
        val apiJson = Json { ignoreUnknownKeys = true }
        val payload = javaClass.getResource("/catalog-response.json")!!.readText()
        val screenshotUrlsInApiResponse = apiJson
            .parseToJsonElement(payload)
            .jsonObject["screenshotUrlList"]!!
            .jsonArray
            .map { it.jsonPrimitive.content }
        assertEquals(5, screenshotUrlsInApiResponse.size)

        val dto = apiJson.decodeFromString<AppDetailsDto>(payload)
        assertEquals(screenshotUrlsInApiResponse, dto.screenshots)

        val domain = AppDetailsMapper().toDomain(dto)
        val restored = AppDetailsEntityMapper().toDomain(AppDetailsEntityMapper().toEntity(domain))

        assertEquals(screenshotUrlsInApiResponse, restored.screenshotUrlList)
    }
}
