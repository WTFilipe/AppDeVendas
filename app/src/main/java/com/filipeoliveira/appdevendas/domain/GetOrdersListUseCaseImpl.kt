package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import com.filipeoliveira.appdevendas.domain.errors.EmptyResponseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOrdersListUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : GetOrdersListUseCase{
    override suspend fun execute(): Flow<Result<List<OrderWithItems>>> = flow {
        salesRepository.getOrderList()
            .catch {
                emit(Result.Error(it))
            }
            .collect{
                if (it.isNotEmpty()){
                    emit(Result.Success(it))
                } else {
                    emit(Result.Error(EmptyResponseError()))
                }
            }
    }
}