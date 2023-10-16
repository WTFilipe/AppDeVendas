package com.filipeoliveira.data.mappers

import com.filipeoliveira.data.local.model.CartItemDB
import com.filipeoliveira.data.local.model.OrderItemDB
import com.filipeoliveira.domain.model.AvailableItem
import java.math.RoundingMode

object AvailableItemMappers {
    fun AvailableItem.toCartItemDB(quantityOfItems: Long, orderID: Long = CartItemDB.CART_ORDER_ID) =
        CartItemDB(
            sku = sku,
            quantityOfItems = quantityOfItems,
            name = name,
            description = description,
            imageURL = imageURL,
            valuePerItem = value.setScale(2, RoundingMode.FLOOR).toString(),
            orderId = orderID,
        )
}