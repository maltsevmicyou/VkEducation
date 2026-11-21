package io.mmaltsev.vkeducation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mmaltsev.vkeducation.data.appdetails.AppListRepositoryImpl
import io.mmaltsev.vkeducation.domain.appdetails.AppListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // @Binds говорит Hilt: "Когда просят AppListRepository, давай экземпляр AppListRepositoryImpl"
    @Binds
    @Singleton
    abstract fun bindAppListRepository(impl: AppListRepositoryImpl): AppListRepository
}