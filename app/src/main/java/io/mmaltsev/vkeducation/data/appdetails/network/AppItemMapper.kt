package io.mmaltsev.vkeducation.data.appdetails.network

import io.mmaltsev.vkeducation.domain.appdetails.AppItem

fun AppCatalogDto.toDomain(): AppItem {
    return AppItem(
        id = id,
        name = name,
        description = description,
        category = category,
        iconUrl = iconUrl
    )
}