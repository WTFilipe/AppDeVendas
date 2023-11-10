package com.filipeoliveira.data.usecases

import com.filipeoliveira.data.repository.FakeSalesRepository
import com.filipeoliveira.data.repository.SalesRepository
import com.filipeoliveira.domain.GetAvailableItemListUseCase
import com.filipeoliveira.domain.errors.EmptyResponseError
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.domain.model.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class GetAvailableItemListUseCaseImplTest {

    private lateinit var fakeSalesRepository: SalesRepository
    private lateinit var useCase: GetAvailableItemListUseCase

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
        useCase = GetAvailableItemListUseCaseImpl(fakeSalesRepository)
    }

    @Test
    fun `Should return Item List, when items are available`() = runBlocking {
        val availableItems = useCase.execute().first()

        assertThat(availableItems).isInstanceOf(Result.Success::class.java)
        assertThat((availableItems as Result.Success).data.size).isEqualTo(4)
    }

    @Test
    fun `Should throw error, when items are available`() = runBlocking {
        fakeSalesRepository = FakeSalesRepository(emptyList())
        useCase = GetAvailableItemListUseCaseImpl(fakeSalesRepository)

        val availableItems = useCase.execute().first()

        assertThat(availableItems).isInstanceOf(Result.Error::class.java)
        assertThat((availableItems as Result.Error).error).isInstanceOf(EmptyResponseError::class.java)
    }
}