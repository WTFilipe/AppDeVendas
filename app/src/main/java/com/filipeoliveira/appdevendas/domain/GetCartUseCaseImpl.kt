package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : GetCartUseCase{
    override suspend fun execute(): Flow<Result<OrderWithItems>> = flow {
        salesRepository.getCart()
            .catch {
                emit(Result.Error(it))
            }
            .collect{
                emit(Result.Success(it))
            }
    }
}