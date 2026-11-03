package com.vk.simplerepoexample.data

import com.vk.simplerepoexample.data.dto.App
import com.vk.simplerepoexample.data.dto.AppEntity

internal class Mapper {

    fun toApp(appEntity: AppEntity): App {
        return App(
            id = appEntity.id,
            name = appEntity.name,
            description = appEntity.description,
            category = appEntity.category,
            iconUrl = appEntity.iconUrl,
        )
    }

    fun toAppEntity(app: App): AppEntity {
        return AppEntity(
            id = app.id,
            name = app.name,
            description = app.description,
            category = app.category,
            iconUrl = app.iconUrl,
        )
    }

}