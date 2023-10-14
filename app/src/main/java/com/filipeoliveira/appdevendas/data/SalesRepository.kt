package com.filipeoliveira.appdevendas.data

import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import kotlinx.coroutines.flow.Flow

interface SalesRepository {
    suspend fun getAvailableItemList() : Flow<List<AvailableItem>>
    suspend fun getOrderList() : Flow<List<OrderWithItems>>
    suspend fun getCart() : Flow<OrderWithItems>
}