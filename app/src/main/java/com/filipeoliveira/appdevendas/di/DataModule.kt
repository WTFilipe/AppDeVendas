package com.filipeoliveira.appdevendas.di

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.SalesRepositoryImpl
import com.filipeoliveira.appdevendas.data.local.SalesLocalData
import com.filipeoliveira.appdevendas.data.local.SalesLocalDataImpl
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteData
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteDataImpl
import com.filipeoliveira.appdevendas.domain.AddToCartUseCase
import com.filipeoliveira.appdevendas.domain.AddToCartUseCaseImpl
import com.filipeoliveira.appdevendas.domain.FinishPurchaseUseCase
import com.filipeoliveira.appdevendas.domain.FinishPurchaseUseCaseImpl
import com.filipeoliveira.appdevendas.domain.GetAvailableItemListUseCase
import com.filipeoliveira.appdevendas.domain.GetAvailableItemListUseCaseImpl
import com.filipeoliveira.appdevendas.domain.GetCartUseCase
import com.filipeoliveira.appdevendas.domain.GetCartUseCaseImpl
import com.filipeoliveira.appdevendas.domain.GetOrdersListUseCase
import com.filipeoliveira.appdevendas.domain.GetOrdersListUseCaseImpl
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
    abstract fun providesSalesLocalData(localData: SalesLocalDataImpl) : SalesLocalData
    @Binds
    abstract fun providesSalesRepository(salesRepositoryImpl: SalesRepositoryImpl) : SalesRepository
    @Binds
    abstract fun providesGetAvailableItemListUseCase(getAvailableItemListUseCase: GetAvailableItemListUseCaseImpl) : GetAvailableItemListUseCase
    @Binds
    abstract fun providesGetCartUseCase(getCartUseCaseImpl: GetCartUseCaseImpl) : GetCartUseCase
    @Binds
    abstract fun providesAddToCartUseCase(addToCartUseCaseImpl: AddToCartUseCaseImpl) : AddToCartUseCase
    @Binds
    abstract fun providesFinishPurchaseUseCase(finishPurchaseUseCaseImpl: FinishPurchaseUseCaseImpl) : FinishPurchaseUseCase
    @Binds
    abstract fun providesGetOrdersListUseCase(getOrdersListUseCaseImpl: GetOrdersListUseCaseImpl) : GetOrdersListUseCase
}