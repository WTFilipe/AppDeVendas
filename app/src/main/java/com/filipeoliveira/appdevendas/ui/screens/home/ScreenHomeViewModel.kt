package com.filipeoliveira.appdevendas.ui.screens.home

import com.filipeoliveira.appdevendas.data.model.AvailableItem

interface ScreenHomeViewModel {
    fun loadAvailableItems()
    fun getCart()
    fun addToCart(availableItem: AvailableItem, quantity: Long)
}