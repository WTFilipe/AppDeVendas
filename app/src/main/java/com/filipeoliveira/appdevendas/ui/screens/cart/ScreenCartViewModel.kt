package com.filipeoliveira.appdevendas.ui.screens.cart

import com.filipeoliveira.domain.model.OrderWithItems

interface ScreenCartViewModel {
    fun getCart()
    fun finishPurchase(orderWithItems: OrderWithItems)
}