package com.filipeoliveira.domain

import com.filipeoliveira.domain.model.AvailableItem

interface AddToCartUseCase {
    suspend fun execute(availableItem: AvailableItem, selectedQuantity: Long)
}