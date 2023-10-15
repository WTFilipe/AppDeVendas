package com.filipeoliveira.appdevendas.data.model

import com.filipeoliveira.appdevendas.data.local.model.OrderWithItemsDB
import java.math.BigDecimal

data class OrderWithItems (
    val order: Order,
    val items: List<OrderItem>
) {
    val orderValue: BigDecimal
        get() = items.sumOf { it.totalValue }

    val quantityOfItems: Long
        get() = items.sumOf { it.quantityOfItems }

    fun toOrderWithItemsDB() = OrderWithItemsDB(orderDB = order.toOrderDB(), items = items.map { it.toOrderItemDB() })
}