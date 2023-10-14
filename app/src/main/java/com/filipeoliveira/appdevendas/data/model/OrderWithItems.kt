package com.filipeoliveira.appdevendas.data.model

data class OrderWithItems (
    val order: Order,
    val items: List<OrderItem>
)