package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.FakeSalesRepository
import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.AddToCartUseCase
import com.filipeoliveira.domain.model.AvailableItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class AddToCartUseCaseImplTest {

    private lateinit var fakeSalesRepository: SalesRepository
    private lateinit var usecase: AddToCartUseCase
    @Before
    fun setUp() {
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
        fakeSalesRepository = FakeSalesRepository(availableItems)
        usecase = AddToCartUseCaseImpl(fakeSalesRepository)
    }

    @Test
    fun `Item added to cart, should return in cart response`() = runBlocking {
        val items = fakeSalesRepository.getAvailableItemList().first()

        items.forEachIndexed { index, availableItem ->
            usecase.execute(availableItem, index.toLong())
        }

        val cart = fakeSalesRepository.getCart().first()
        cart.items.forEachIndexed { index, orderItem ->
            assertThat(orderItem.sku).isEqualTo(items[index].sku)
            assertThat(orderItem.quantityOfItems).isEqualTo(index)
        }
    }
}