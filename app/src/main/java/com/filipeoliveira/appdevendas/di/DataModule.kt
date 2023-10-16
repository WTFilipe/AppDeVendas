package com.filipeoliveira.appdevendas.di

import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.data.repository.SalesRepositoryImpl
import com.filipeoliveira.data.local.SalesLocalData
import com.filipeoliveira.data.local.SalesLocalDataImpl
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteData
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteDataImpl
import com.filipeoliveira.domain.AddToCartUseCase
import com.filipeoliveira.data.usecases.AddToCartUseCaseImpl
import com.filipeoliveira.domain.FinishPurchaseUseCase
import com.filipeoliveira.data.usecases.FinishPurchaseUseCaseImpl
import com.filipeoliveira.domain.GetAvailableItemListUseCase
import com.filipeoliveira.data.usecases.GetAvailableItemListUseCaseImpl
import com.filipeoliveira.domain.GetCartUseCase
import com.filipeoliveira.data.usecases.GetCartUseCaseImpl
import com.filipeoliveira.domain.GetOrdersListUseCase
import com.filipeoliveira.data.usecases.GetOrdersListUseCaseImpl
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