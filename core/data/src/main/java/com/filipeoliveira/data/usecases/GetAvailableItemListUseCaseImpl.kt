package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.GetAvailableItemListUseCase
import com.filipeoliveira.domain.errors.EmptyResponseError
import com.filipeoliveira.domain.model.AvailableItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.filipeoliveira.domain.model.Result

class GetAvailableItemListUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : GetAvailableItemListUseCase {
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