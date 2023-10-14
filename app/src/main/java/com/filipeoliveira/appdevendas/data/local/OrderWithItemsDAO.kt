package com.filipeoliveira.appdevendas.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.filipeoliveira.appdevendas.data.local.model.OrderWithItemsDB
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderWithItemsDAO {
    @Transaction
    @Query("SELECT * FROM OrderDB WHERE isStillInCart = 0")
    fun getOrdersWithItemsList() : Flow<List<OrderWithItemsDB>>

    @Transaction
    @Query("SELECT * FROM OrderDB WHERE isStillInCart = 1")
    fun getCart() : Flow<List<OrderWithItemsDB>>
}