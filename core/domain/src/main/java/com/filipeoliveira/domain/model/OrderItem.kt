package com.filipeoliveira.domain.model

import java.math.BigDecimal

data class OrderItem(
    val sku: String,
    val quantityOfItems: Long,
    val name: String,
    val description: String,
    val imageURL: String,
    val valuePerItem: BigDecimal,
    val orderId: Long
) {
    val totalValue: BigDecimal
        get() = quantityOfItems.toBigDecimal() * valuePerItem
}