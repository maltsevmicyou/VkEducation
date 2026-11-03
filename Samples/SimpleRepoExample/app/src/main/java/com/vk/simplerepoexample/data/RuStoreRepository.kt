package com.vk.simplerepoexample.data

import com.vk.simplerepoexample.data.dto.App

interface RuStoreRepository {
    fun getApplications(
        onSuccess: (List<App>, type: String) -> Unit,
        onFailed: (Throwable) -> Unit
    )
}