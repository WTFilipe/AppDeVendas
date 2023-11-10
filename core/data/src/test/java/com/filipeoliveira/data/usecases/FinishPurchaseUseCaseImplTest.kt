package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.FakeSalesRepository
import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.FinishPurchaseUseCase
import com.filipeoliveira.domain.model.AvailableItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class FinishPurchaseUseCaseImplTest {

    private lateinit var fakeRepository: SalesRepository
    private lateinit var useCase: FinishPurchaseUseCase

    @Before
    fun setUp(){
        val availableItems = mutableListOf<AvailableItem>()
        for (i in 0..3){
            availableItems.add(
                AvailableItem(
                    sku = i.toString(),
                    name = i.toString(),
                    imageURL = i.toString(),
                    value = BigDecimal(i),
                    description = i.toString()
                )
            )
        }
        fakeRepository = FakeSalesRepository(availableItems)
        useCase = FinishPurchaseUseCaseImpl(fakeRepository)
    }

    @Test
    fun `Finished order, should have id greater than -1`() = runBlocking {
        val availableItems = fakeRepository.getAvailableItemList().first()
        availableItems.forEachIndexed { index, availableItem ->
            fakeRepository.addToCart(availableItem, index.toLong())
        }
        val cart = fakeRepository.getCart().first()

        useCase.execute(cart)

        val finishedOrders = fakeRepository.getOrderList().first()
        assertThat(finishedOrders.size).isEqualTo(1)
        assertThat(finishedOrders[0].order.orderId).isGreaterThan(-1)
    }
}