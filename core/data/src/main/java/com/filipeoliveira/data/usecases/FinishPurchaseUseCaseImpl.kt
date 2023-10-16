package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.FinishPurchaseUseCase
import com.filipeoliveira.domain.model.OrderWithItems
import javax.inject.Inject

class FinishPurchaseUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : FinishPurchaseUseCase {
    override suspend fun execute(orderWithItems: OrderWithItems)  {
        salesRepository.finishPurchase(orderWithItems)
    }
}