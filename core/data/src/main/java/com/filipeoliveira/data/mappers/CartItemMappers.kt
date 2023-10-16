package com.filipeoliveira.data.mappers

import com.filipeoliveira.data.local.model.CartItemDB
import com.filipeoliveira.domain.model.OrderItem
import java.math.BigDecimal
import java.math.RoundingMode

object CartItemMappers {
    fun CartItemDB.toOrderItem() = OrderItem(
        sku = sku,
        quantityOfItems = quantityOfItems,
        name = name,
        description = description,
        imageURL = imageURL,
        valuePerItem = BigDecimal(valuePerItem).setScale(2, RoundingMode.FLOOR),
        orderId = orderId
    )
}