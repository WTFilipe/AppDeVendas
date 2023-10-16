package com.filipeoliveira.data.mappers

import com.filipeoliveira.data.local.model.OrderDB
import com.filipeoliveira.data.local.model.OrderItemDB
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.OrderItem
import java.math.RoundingMode
import java.math.BigDecimal

object OrderItemMappers {
    fun OrderItem.toOrderItemDB() = OrderItemDB(
        sku = sku,
        quantityOfItems = quantityOfItems,
        name = name,
        description = description,
        imageURL = imageURL,
        valuePerItem = valuePerItem.setScale(2, RoundingMode.FLOOR).toString(),
        orderId = orderId
    )

    fun OrderItemDB.toOrderItem() = OrderItem(
        sku = sku,
        quantityOfItems = quantityOfItems,
        name = name,
        description = description,
        imageURL = imageURL,
        valuePerItem = BigDecimal(valuePerItem).setScale(2, RoundingMode.FLOOR),
        orderId = orderId
    )

    fun AvailableItem.toOrderItemDB(quantityOfItems: Long, orderID: Long = OrderDB.CART_ORDER_ID) =
        OrderItemDB(
            sku = sku,
            quantityOfItems = quantityOfItems,
            name = name,
            description = description,
            imageURL = imageURL,
            valuePerItem = value.setScale(2, RoundingMode.FLOOR).toString(),
            orderId = orderID,
        )
}