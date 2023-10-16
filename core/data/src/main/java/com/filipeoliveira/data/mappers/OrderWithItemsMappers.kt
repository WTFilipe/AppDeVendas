package com.filipeoliveira.data.mappers

import com.filipeoliveira.data.local.model.OrderWithItemsDB
import com.filipeoliveira.data.mappers.OrderItemMappers.toOrderItem
import com.filipeoliveira.data.mappers.OrderItemMappers.toOrderItemDB
import com.filipeoliveira.data.mappers.OrdersMappers.toOrder
import com.filipeoliveira.data.mappers.OrdersMappers.toOrderDB
import com.filipeoliveira.domain.model.OrderWithItems

object OrderWithItemsMappers {
    fun OrderWithItems.toOrderWithItemsDB() = OrderWithItemsDB(orderDB = order.toOrderDB(), items = items.map { it.toOrderItemDB() })

    fun OrderWithItemsDB.toOrderWithItems() = OrderWithItems(order = orderDB.toOrder(), items = items.map { it.toOrderItem() })
}