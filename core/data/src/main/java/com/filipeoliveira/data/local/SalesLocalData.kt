package com.filipeoliveira.appdevendas.data.local

import com.filipeoliveira.data.local.model.OrderItemDB
import com.filipeoliveira.data.local.model.OrderWithItemsDB
import kotlinx.coroutines.flow.Flow

interface SalesLocalData {
    suspend fun getOrderWithItemsList() : Flow<List<OrderWithItemsDB>>
    suspend fun getCart() : Flow<List<OrderWithItemsDB>>
    suspend fun addToCart(item: OrderItemDB)
    suspend fun finishPurchase(orderWithItems: OrderWithItemsDB)
}