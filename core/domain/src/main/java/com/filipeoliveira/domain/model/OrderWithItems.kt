package com.filipeoliveira.domain.model

import java.math.BigDecimal

data class OrderWithItems (
    val order: Order,
    val items: List<OrderItem>
) {
    val orderValue: BigDecimal
        get() = items.sumOf { it.totalValue }

    val quantityOfItems: Long
        get() = items.sumOf { it.quantityOfItems }
}