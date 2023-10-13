package com.filipeoliveira.appdevendas.di

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.SalesRepositoryImpl
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteData
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteDataImpl
import com.filipeoliveira.appdevendas.domain.GetAvailableItemListUseCase
import com.filipeoliveira.appdevendas.domain.GetAvailableItemListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun providesSalesRemoteData(remoteData: SalesRemoteDataImpl) : SalesRemoteData
    @Binds
    abstract fun providesSalesRepository(salesRepositoryImpl: SalesRepositoryImpl) : SalesRepository
    @Binds
    abstract fun providesGetAvailableItemListUseCase(getAvailableItemListUseCase: GetAvailableItemListUseCaseImpl) : GetAvailableItemListUseCase
}