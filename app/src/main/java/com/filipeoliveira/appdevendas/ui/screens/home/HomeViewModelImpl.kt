package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.domain.AddToCartUseCase
import com.filipeoliveira.appdevendas.domain.GetAvailableItemListUseCase
import com.filipeoliveira.appdevendas.domain.GetCartUseCase
import com.filipeoliveira.appdevendas.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getAvailableItemListUseCase: GetAvailableItemListUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel(), HomeViewModel {

    private var _homeScreenModel = MutableStateFlow(
        HomeScreenModel(
            isLoading = true,
            error = null,
            availableAvailableItemList = emptyList()
        )
    )
    val homeScreenModel: StateFlow<HomeScreenModel>
        get() = _homeScreenModel

    init {
        this.loadAvailableItems()
        this.getCart()
    }

    override fun loadAvailableItems() {
        _homeScreenModel = MutableStateFlow(
            HomeScreenModel(
                isLoading = true,
                error = null,
                availableAvailableItemList = emptyList()
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            getAvailableItemListUseCase.execute()
                .catch {
                    _homeScreenModel.value = HomeScreenModel(
                        isLoading = false,
                        error = it,
                        availableAvailableItemList = emptyList()
                    )
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _homeScreenModel.value = HomeScreenModel(
                                isLoading = false,
                                error = null,
                                availableAvailableItemList = result.data
                            )
                        }

                        is Result.Error -> {
                            _homeScreenModel.value = HomeScreenModel(
                                isLoading = false,
                                error = result.error,
                                availableAvailableItemList = emptyList()
                            )
                        }
                    }
                }
        }
    }

    override fun getCart() {
        viewModelScope.launch(Dispatchers.IO) {
            getCartUseCase.execute()
                .catch {
                    _homeScreenModel.value = _homeScreenModel.value.copy(
                        cartPrice = BigDecimal.ZERO,
                        cartItemQuantity = 0L
                    )
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _homeScreenModel.value = _homeScreenModel.value.copy(
                                cartPrice = result.data.orderValue,
                                cartItemQuantity = result.data.quantityOfItems
                            )
                        }

                        is Result.Error -> {
                            _homeScreenModel.value = _homeScreenModel.value.copy(
                                cartPrice = BigDecimal.ZERO,
                                cartItemQuantity = 0L
                            )
                        }
                    }
                }
        }
    }

    override fun addToCard(availableItem: AvailableItem, quantity: Long) {
       viewModelScope.launch(Dispatchers.IO) {
           addToCartUseCase.execute(availableItem, quantity)
       }
    }
}