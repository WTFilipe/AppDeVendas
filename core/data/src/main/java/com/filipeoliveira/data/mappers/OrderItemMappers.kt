package com.filipeoliveira.data.mappers

import com.filipeoliveira.data.local.model.OrderItemDB
import com.filipeoliveira.domain.model.OrderItem
import java.math.BigDecimal
import java.math.RoundingMode

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

}