package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import kotlinx.coroutines.flow.Flow

interface AddToCartUseCase {
    suspend fun execute(availableItem: AvailableItem, selectedQuantity: Long)
}