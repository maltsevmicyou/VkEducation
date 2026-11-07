package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.domain.appdetails.AppItem

// Mapper превращает DTO в Domain-модель
fun AppItemDto.toDomain(): AppItem {
    return AppItem(
        id = id,
        name = name,
        description = description,
        category = category,
        iconResId = iconResId
    )
}

// Обратный маппер (если понадобится)
fun AppItem.toDto(): AppItemDto {
    return AppItemDto(
        id = id,
        name = name,
        description = description,
        category = category,
        iconResId = iconResId
    )
}