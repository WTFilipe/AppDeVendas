package com.filipeoliveira.data.mappers

import com.filipeoliveira.domain.model.Order
import com.filipeoliveira.data.local.model.OrderDB

object OrdersMappers {

    fun OrderDB.toOrder() = Order(orderId = orderId)

    fun Order.toOrderDB() = OrderDB(orderId = orderId)
}