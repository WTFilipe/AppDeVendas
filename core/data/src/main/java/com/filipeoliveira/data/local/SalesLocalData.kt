package com.filipeoliveira.data.local

import com.filipeoliveira.data.local.model.CartItemDB
import com.filipeoliveira.data.local.model.OrderWithItemsDB
import kotlinx.coroutines.flow.Flow

interface SalesLocalData {
    suspend fun getOrderWithItemsList() : Flow<List<OrderWithItemsDB>>
    suspend fun getCart() : Flow<List<CartItemDB>>
    suspend fun addToCart(cartItemDB: CartItemDB)
    suspend fun finishPurchase(orderWithItems: OrderWithItemsDB)
}