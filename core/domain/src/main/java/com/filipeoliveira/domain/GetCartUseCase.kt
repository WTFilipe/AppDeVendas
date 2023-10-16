package com.filipeoliveira.domain

import com.filipeoliveira.domain.model.OrderWithItems
import com.filipeoliveira.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface GetCartUseCase {
    suspend fun execute() : Flow<Result<OrderWithItems>>
}