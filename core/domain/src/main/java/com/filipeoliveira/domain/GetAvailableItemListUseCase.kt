package com.filipeoliveira.domain

import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface GetAvailableItemListUseCase {
    suspend fun execute() : Flow<Result<List<AvailableItem>>>
}