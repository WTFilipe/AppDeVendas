package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import kotlinx.coroutines.flow.Flow

interface GetCartUseCase {
    suspend fun execute() : Flow<Result<OrderWithItems>>
}