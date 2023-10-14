package com.filipeoliveira.appdevendas.di

import android.content.Context
import androidx.room.Room
import com.filipeoliveira.appdevendas.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room
            .databaseBuilder(context = applicationContext, AppDatabase::class.java, "app-database")
            .build()
    }
}