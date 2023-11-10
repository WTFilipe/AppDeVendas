package com.filipeoliveira.data.repository

import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.Order
import com.filipeoliveira.domain.model.OrderItem
import com.filipeoliveira.domain.model.OrderWithItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class FakeSalesRepository(private val availableItems: List<AvailableItem>) : SalesRepository {

    private lateinit var cart: OrderWithItems

    private val orders = mutableListOf<OrderWithItems>()

    override suspend fun getAvailableItemList(): Flow<List<AvailableItem>> {
        return flow { emit(availableItems) }
    }

    override suspend fun getOrderList(): Flow<List<OrderWithItems>> {
        return flow { emit(orders) }
    }

    override suspend fun getCart(): Flow<OrderWithItems> {
        return flow { emit(cart) }
    }

    override suspend fun addToCart(availableItem: AvailableItem, selectedQuantity: Long) {
        if (!this::cart.isInitialized) {
            cart = OrderWithItems(
                order = Order(-1L), items = listOf(
                    OrderItem(
                        sku = availableItem.sku,
                        quantityOfItems = selectedQuantity,
                        name = availableItem.name,
                        description = availableItem.description,
                        imageURL = availableItem.imageURL,
                        valuePerItem = availableItem.value,
                        orderId = -1L
                    )
                )
            )
        } else {
            cart = cart.copy(
                items = cart.items + listOf(
                    OrderItem(
                        sku = availableItem.sku,
                        quantityOfItems = selectedQuantity,
                        name = availableItem.name,
                        description = availableItem.description,
                        imageURL = availableItem.imageURL,
                        valuePerItem = availableItem.value,
                        orderId = -1L
                    )
                )
            )
        }

    }

    override suspend fun finishPurchase(orderWithItems: OrderWithItems) {
        val finishedOrder = cart.copy(order = Order(Random.nextLong(0, 100)))
        orders.add(finishedOrder)
    }
}