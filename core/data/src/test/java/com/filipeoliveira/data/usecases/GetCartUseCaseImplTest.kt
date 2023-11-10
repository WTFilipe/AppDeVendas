package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.FakeSalesRepository
import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.GetCartUseCase
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class GetCartUseCaseImplTest{

    private lateinit var fakeSalesRepository: SalesRepository
    private lateinit var useCase: GetCartUseCase

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
        useCase = GetCartUseCaseImpl(fakeSalesRepository)
    }

    @Test
    fun `Item added to cart, should return in cart response`() = runBlocking {
        val items = fakeSalesRepository.getAvailableItemList().first()

        items.forEachIndexed { index, availableItem ->
            fakeSalesRepository.addToCart(availableItem, index.toLong())
        }

        val cart = useCase.execute().first()
        assertThat(cart).isInstanceOf(Result.Success::class.java)
        assertThat((cart as Result.Success).data.items.size).isEqualTo(4)
    }
}