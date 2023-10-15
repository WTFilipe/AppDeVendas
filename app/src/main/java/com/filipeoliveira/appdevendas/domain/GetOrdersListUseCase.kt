package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import kotlinx.coroutines.flow.Flow

interface GetOrdersListUseCase {
    suspend fun execute() : Flow<Result<List<OrderWithItems>>>
}