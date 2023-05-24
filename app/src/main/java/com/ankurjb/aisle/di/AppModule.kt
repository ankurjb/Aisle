package com.ankurjb.aisle.di

import com.ankurjb.aisle.ApiMapper
import com.ankurjb.aisle.network.ApiClient
import com.ankurjb.aisle.repo.MainRepository
import com.ankurjb.aisle.repo.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesMainRepository(
        apiClient: ApiClient,
        apiMapper: ApiMapper
    ): MainRepository = MainRepositoryImpl(apiClient, apiMapper)

}
