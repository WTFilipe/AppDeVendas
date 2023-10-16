package com.filipeoliveira.appdevendas.data.remote

import com.filipeoliveira.appdevendas.data.remote.model.ItemRemote
import kotlinx.coroutines.flow.Flow

interface SalesRemoteData {
    suspend fun getAvailableItemsList() : Flow<List<ItemRemote>>
}