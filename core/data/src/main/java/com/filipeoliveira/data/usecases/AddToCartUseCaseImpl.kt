package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.AddToCartUseCase
import javax.inject.Inject

class AddToCartUseCaseImpl @Inject constructor (
    private val salesRepository: SalesRepository
) : AddToCartUseCase {
    override suspend fun execute(availableItem: AvailableItem, selectedQuantity: Long) {
        salesRepository.addToCart(availableItem, selectedQuantity)
    }
}