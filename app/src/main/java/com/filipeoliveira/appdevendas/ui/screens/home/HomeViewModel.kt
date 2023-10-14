package com.filipeoliveira.appdevendas.ui.screens.home

import com.filipeoliveira.appdevendas.data.model.AvailableItem

interface HomeViewModel {
    fun loadAvailableItems()
    fun getCart()
    fun addToCard(availableItem: AvailableItem, quantity: Long)
}