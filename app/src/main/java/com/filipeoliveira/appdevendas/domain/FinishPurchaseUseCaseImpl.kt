package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import javax.inject.Inject

class FinishPurchaseUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : FinishPurchaseUseCase{
    override suspend fun execute(orderWithItems: OrderWithItems)  {
        salesRepository.addToCart(orderWithItems)
    }
}