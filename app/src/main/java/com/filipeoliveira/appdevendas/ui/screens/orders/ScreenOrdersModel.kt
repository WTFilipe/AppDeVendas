package com.filipeoliveira.appdevendas.ui.screens.orders

import com.filipeoliveira.appdevendas.data.model.OrderWithItems

data class ScreenOrdersModel(
    val isLoading: Boolean,
    val error: Throwable?,
    val orders: List<OrderWithItems>
)
