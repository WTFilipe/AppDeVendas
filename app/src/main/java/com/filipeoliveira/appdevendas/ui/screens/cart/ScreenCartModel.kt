package com.filipeoliveira.appdevendas.ui.screens.cart

import com.filipeoliveira.appdevendas.data.model.OrderWithItems

data class ScreenCartModel(
    val isLoading: Boolean,
    val error: Throwable?,
    val cart: OrderWithItems
)
