package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.FakeSalesRepository
import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.GetOrdersListUseCase
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class GetOrdersListUseCaseImplTest {

    private lateinit var fakeSalesRepository: SalesRepository
    private lateinit var useCase: GetOrdersListUseCase

    @Before
    fun setUp() {
        val availableItems = mutableListOf<AvailableItem>()
        for (i in 0..3) {
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
        fakeSalesRepository = FakeSalesRepository(availableItems)
        useCase = GetOrdersListUseCaseImpl(fakeSalesRepository)
    }

    @Test
    fun `Finished purchases should appear in order list`() = runBlocking {
        val availableItems = fakeSalesRepository.getAvailableItemList().first()

        fakeSalesRepository.addToCart(availableItems[0], 1)
        fakeSalesRepository.addToCart(availableItems[1], 1)
        val cart = fakeSalesRepository.getCart().first()
        fakeSalesRepository.finishPurchase(cart)

        fakeSalesRepository.addToCart(availableItems[2], 1)
        fakeSalesRepository.addToCart(availableItems[3], 1)
        val secondCart = fakeSalesRepository.getCart().first()
        fakeSalesRepository.finishPurchase(secondCart)

        val finishedOrders = useCase.execute().first()

        assertThat(finishedOrders).isInstanceOf(Result.Success::class.java)
        assertThat((finishedOrders as Result.Success).data.size).isEqualTo(2)
    }
}