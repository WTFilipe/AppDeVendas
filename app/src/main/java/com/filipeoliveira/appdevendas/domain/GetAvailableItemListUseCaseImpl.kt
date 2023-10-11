package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetAvailableItemListUseCaseImpl(
    private val salesRepository: SalesRepository
) : GetAvailableItemListUseCase{
    override suspend fun execute(): Flow<Result<List<Item>>> = flow {
        salesRepository.getAvailableItemList()
            .catch {
                emit(Result.Error(it))
            }
            .collect{
                emit(Result.Success(it))
            }
    }
}