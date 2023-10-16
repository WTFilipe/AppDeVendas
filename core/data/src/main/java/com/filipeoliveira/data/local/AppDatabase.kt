package com.filipeoliveira.appdevendas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filipeoliveira.data.local.model.OrderDB
import com.filipeoliveira.data.local.model.OrderItemDB

@Database(entities = [OrderDB::class, OrderItemDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getOrderWithItemsDao() : OrderWithItemsDAO
}