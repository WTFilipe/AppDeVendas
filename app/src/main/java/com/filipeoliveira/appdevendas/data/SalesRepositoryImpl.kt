package com.filipeoliveira.appdevendas.data

import com.filipeoliveira.appdevendas.data.local.SalesLocalData
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.data.model.Order
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteData
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
        val cart = list.firstOrNull()

        cart?.toOrderWithItems() ?: kotlin.run {
            OrderWithItems(
                order = Order(orderId = 0),
                items = emptyList()
            )
        }
    }

    override suspend fun addToCart(availableItem: AvailableItem, selectedQuantity: Long) {
        localData.addToCart(availableItem.toOrderItemDB(selectedQuantity))
    }

    override suspend fun addToCart(orderWithItems: OrderWithItems) {
        localData.finishPurchase(orderWithItems.toOrderWithItemsDB())
    }

}