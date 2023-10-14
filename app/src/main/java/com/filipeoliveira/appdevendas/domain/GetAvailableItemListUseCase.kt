package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.model.AvailableItem
import kotlinx.coroutines.flow.Flow

interface GetAvailableItemListUseCase {
    suspend fun execute() : Flow<Result<List<AvailableItem>>>
}