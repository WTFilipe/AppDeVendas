package com.filipeoliveira.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
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
}