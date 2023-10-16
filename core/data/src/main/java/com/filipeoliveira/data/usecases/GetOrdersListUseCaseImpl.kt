package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.model.OrderWithItems
import com.filipeoliveira.domain.GetOrdersListUseCase
import com.filipeoliveira.domain.errors.EmptyResponseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.filipeoliveira.domain.model.Result

class GetOrdersListUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : GetOrdersListUseCase {
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