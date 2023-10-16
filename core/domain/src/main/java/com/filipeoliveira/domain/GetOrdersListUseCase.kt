package com.filipeoliveira.domain

import com.filipeoliveira.domain.model.OrderWithItems
import com.filipeoliveira.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface GetOrdersListUseCase {
    suspend fun execute() : Flow<Result<List<OrderWithItems>>>
}