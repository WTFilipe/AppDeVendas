package com.filipeoliveira.domain

import com.filipeoliveira.domain.model.OrderWithItems

interface FinishPurchaseUseCase {
    suspend fun execute(orderWithItems: OrderWithItems)
}