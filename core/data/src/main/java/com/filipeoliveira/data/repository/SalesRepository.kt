package com.filipeoliveira.data.repository

import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.OrderWithItems
import kotlinx.coroutines.flow.Flow

interface SalesRepository {
    suspend fun getAvailableItemList() : Flow<List<AvailableItem>>
    suspend fun getOrderList() : Flow<List<OrderWithItems>>
    suspend fun getCart() : Flow<OrderWithItems>
    suspend fun addToCart(availableItem: AvailableItem, selectedQuantity: Long)
    suspend fun finishPurchase(orderWithItems: OrderWithItems)
}