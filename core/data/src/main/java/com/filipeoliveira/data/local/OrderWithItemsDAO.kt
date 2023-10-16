package com.filipeoliveira.appdevendas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.filipeoliveira.data.local.model.CartItemDB
import com.filipeoliveira.data.local.model.OrderDB
import com.filipeoliveira.data.local.model.OrderItemDB
import com.filipeoliveira.data.local.model.OrderWithItemsDB
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderWithItemsDAO {
    @Transaction
    @Query("SELECT * FROM OrderDB")
    fun getOrdersWithItemsList() : Flow<List<OrderWithItemsDB>>
    @Transaction
    @Query("SELECT * FROM CartItemDB")
    fun getCart() : Flow<List<CartItemDB>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(orderDB: OrderDB)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(cartItemDB: CartItemDB)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderItem(orderItemDB: OrderItemDB)
    @Query("DELETE FROM OrderItemDB WHERE orderId == -1 AND sku == :sku")
    fun deleteCartItem(sku: String)
    @Query("DELETE FROM CartItemDB")
    fun deleteCart()
}