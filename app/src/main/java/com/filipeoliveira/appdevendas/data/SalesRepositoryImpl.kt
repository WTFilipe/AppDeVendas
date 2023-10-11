package com.filipeoliveira.appdevendas.data

import com.filipeoliveira.appdevendas.data.model.Item
import com.filipeoliveira.appdevendas.data.remote.SalesRemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SalesRepositoryImpl(
    private val remoteData: SalesRemoteData
): SalesRepository {
    override suspend fun getAvailableItemList(): Flow<List<Item>> = remoteData.getAvailableItemsList().map {list ->
        list.map { item ->
            item.toItem()
        }
    }

}