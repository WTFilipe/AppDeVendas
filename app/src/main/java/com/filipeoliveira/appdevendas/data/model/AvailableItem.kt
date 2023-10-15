package com.filipeoliveira.appdevendas.data.model

import com.filipeoliveira.appdevendas.data.local.model.OrderDB
import com.filipeoliveira.appdevendas.data.local.model.OrderItemDB
import java.math.BigDecimal
import java.math.RoundingMode

data class AvailableItem(
    val sku: String,
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
) {
    fun toOrderItemDB(quantityOfItems: Long, orderID: Long = OrderDB.CART_ORDER_ID) = OrderItemDB(
        sku = sku,
        quantityOfItems = quantityOfItems,
        name = name,
        description = description,
        imageURL = imageURL,
        valuePerItem = value.setScale(2, RoundingMode.FLOOR).toString(),
        orderId = orderID,
    )
}