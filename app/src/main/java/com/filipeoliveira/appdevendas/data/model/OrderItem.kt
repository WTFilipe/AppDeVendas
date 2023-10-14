package com.filipeoliveira.appdevendas.data.model

import androidx.room.Entity
import java.math.BigDecimal

@Entity
data class OrderItem (
    val sku: String,
    val quantityOfItems: Int,
    val name: String,
    val description: String,
    val imageURL: String,
    val valuePerItem: BigDecimal,
    val totalValue: BigDecimal,
    val orderId: Long
)