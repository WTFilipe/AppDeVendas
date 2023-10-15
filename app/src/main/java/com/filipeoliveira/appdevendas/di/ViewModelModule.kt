package com.filipeoliveira.appdevendas.di

import com.filipeoliveira.appdevendas.ui.screens.home.ScreenHomeViewModel
import com.filipeoliveira.appdevendas.ui.screens.home.ScreenHomeViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun providesHomeViewModel(homeViewModelImpl: ScreenHomeViewModelImpl) : ScreenHomeViewModel
}