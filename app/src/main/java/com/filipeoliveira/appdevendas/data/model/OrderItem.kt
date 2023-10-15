package com.filipeoliveira.appdevendas.data.model

import androidx.room.Entity
import com.filipeoliveira.appdevendas.data.local.model.OrderItemDB
import java.math.BigDecimal
import java.math.RoundingMode

@Entity
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

    fun toOrderItemDB() = OrderItemDB(
        sku = sku,
        quantityOfItems = quantityOfItems,
        name = name,
        description = description,
        imageURL = imageURL,
        valuePerItem = valuePerItem.setScale(2, RoundingMode.FLOOR).toString(),
        orderId = orderId
    )
}