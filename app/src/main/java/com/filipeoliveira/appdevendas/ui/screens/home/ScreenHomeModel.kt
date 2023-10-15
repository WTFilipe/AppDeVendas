package com.filipeoliveira.appdevendas.ui.screens.home

import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.data.model.Order
import com.filipeoliveira.appdevendas.data.model.OrderWithItems

data class ScreenHomeModel(
    val isLoading: Boolean = false,
    val availableAvailableItemList: List<AvailableItem> = emptyList(),
    val error: Throwable? = null,
    val cart: OrderWithItems = OrderWithItems(Order(-1), emptyList()),
) {
    val cartSize = cart.quantityOfItems

    val cartValue = cart.orderValue

    fun getItemSelectedQuantity(sku: String): Long {
        return cart.items.firstOrNull { it.sku == sku }?.quantityOfItems ?: 0
    }
}