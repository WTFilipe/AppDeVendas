package com.filipeoliveira.appdevendas.domain

import com.filipeoliveira.appdevendas.data.SalesRepository
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import javax.inject.Inject

class AddToCartUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : AddToCartUseCase{
    override suspend fun execute(availableItem: AvailableItem, selectedQuantity: Long) {
        salesRepository.addToCart(availableItem, selectedQuantity)
    }
}