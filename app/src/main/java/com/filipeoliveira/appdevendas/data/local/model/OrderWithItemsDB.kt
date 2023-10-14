package com.filipeoliveira.appdevendas.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import java.math.BigDecimal

data class OrderWithItemsDB(
    @Embedded val orderDB: OrderDB,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val items: List<OrderItemDB>
) {
    val orderValue : BigDecimal
        get() = items.sumOf { it.totalValue }

    fun toOrderWithItems() =
        OrderWithItems(order = orderDB.toOrder(), items = items.map { it.toOrderItem() })
}