package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.domain.errors.EmptyResponseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableItemListUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : GetAvailableItemListUseCase{
    override suspend fun execute(): Flow<Result<List<AvailableItem>>> = flow {
        salesRepository.getAvailableItemList()
            .catch {
                emit(Result.Error(it))
            }
            .collect{
                if (it.isEmpty()){
                    emit(Result.Error(EmptyResponseError()))
                } else {
                    emit(Result.Success(it))
                }
            }
    }
}