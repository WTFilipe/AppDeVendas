package com.filipeoliveira.data.repository

import com.filipeoliveira.appdevendas.data.remote.SalesRemoteData
import com.filipeoliveira.data.local.SalesLocalData
import com.filipeoliveira.data.mappers.AvailableItemMappers.toCartItemDB
import com.filipeoliveira.data.mappers.CartItemMappers.toOrderItem
import com.filipeoliveira.data.mappers.OrderWithItemsMappers.toOrderWithItems
import com.filipeoliveira.data.mappers.OrderWithItemsMappers.toOrderWithItemsDB
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.Order
import com.filipeoliveira.domain.model.OrderWithItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SalesRepositoryImpl @Inject constructor (
    private val remoteData: SalesRemoteData,
    private val localData: SalesLocalData
): SalesRepository {
    override suspend fun getAvailableItemList(): Flow<List<AvailableItem>> = remoteData.getAvailableItemsList().map { list ->
        list.map { item ->
            item.toItem()
        }
    }

    override suspend fun getOrderList(): Flow<List<OrderWithItems>> = localData.getOrderWithItemsList().map { list ->
        list.map { item ->
            item.toOrderWithItems()
        }
    }

    override suspend fun getCart(): Flow<OrderWithItems> = localData.getCart().map { list ->
        OrderWithItems(
            order = Order(orderId = 0),
            items = list.map { it.toOrderItem() }
        )
    }

    override suspend fun addToCart(availableItem: AvailableItem, selectedQuantity: Long) {
        localData.addToCart(availableItem.toCartItemDB(selectedQuantity))
    }

    override suspend fun finishPurchase(orderWithItems: OrderWithItems) {
        localData.finishPurchase(orderWithItems.toOrderWithItemsDB())
    }

}