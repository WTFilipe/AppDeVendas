package com.filipeoliveira.appdevendas.ui.screens.cart

import com.filipeoliveira.domain.model.OrderWithItems

data class ScreenCartModel(
    val isLoading: Boolean,
    val error: Throwable?,
    val cart: OrderWithItems
)
