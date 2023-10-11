package com.filipeoliveira.appdevendas.data

import com.filipeoliveira.appdevendas.data.model.Item
import kotlinx.coroutines.flow.Flow

interface SalesRepository {
    suspend fun getAvailableItemList() : Flow<List<Item>>
}