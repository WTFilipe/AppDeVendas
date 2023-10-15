package com.filipeoliveira.appdevendas.ui.screens.cart

import com.filipeoliveira.appdevendas.data.model.OrderWithItems

interface ScreenCartViewModel {
    fun getCart()
    fun finishPurchase(orderWithItems: OrderWithItems)
}