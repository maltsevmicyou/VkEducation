package io.mmaltsev.vkeducation.data.appdetails.local

import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppDetailsEntityMapper {

    fun toEntity(domain: AppDetails): AppDetailsEntity = AppDetailsEntity(
        id = domain.id,
        name = domain.name,
        developer = domain.developer,
        category = domain.category,
        ageRating = domain.ageRating,
        size = domain.size,
        iconUrl = domain.iconUrl,
        screenshots = domain.screenshotUrlList?.let {
            Json.encodeToString(ListSerializer(String.serializer()), it)
        },
        description = domain.description
    )

    fun toDomain(entity: AppDetailsEntity): AppDetails = AppDetails(
        id = entity.id,
        name = entity.name,
        developer = entity.developer,
        category = entity.category,
        ageRating = entity.ageRating,
        size = entity.size,
        iconUrl = entity.iconUrl,
        screenshotUrlList = entity.screenshots?.let { serializedScreenshots ->
            runCatching {
                Json.decodeFromString(ListSerializer(String.serializer()), serializedScreenshots)
            }.getOrNull()
        },
        description = entity.description
    )
}
