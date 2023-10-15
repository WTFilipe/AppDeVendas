package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.model.OrderWithItems

interface FinishPurchaseUseCase {
    suspend fun execute(orderWithItems: OrderWithItems)
}