package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.data.model.Order
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
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
import javax.inject.Inject

@HiltViewModel
class ScreenHomeViewModelImpl @Inject constructor(
    private val getAvailableItemListUseCase: GetAvailableItemListUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel(), ScreenHomeViewModel {

    private var _ScreenHomeModel = MutableStateFlow(
        ScreenHomeModel(
            isLoading = true,
            error = null,
            availableAvailableItemList = emptyList()
        )
    )
    val screenHomeModel: StateFlow<ScreenHomeModel>
        get() = _ScreenHomeModel

    init {
        this.loadAvailableItems()
        this.getCart()
    }

    override fun loadAvailableItems() {
        _ScreenHomeModel = MutableStateFlow(
            ScreenHomeModel(
                isLoading = true,
                error = null,
                availableAvailableItemList = emptyList()
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            getAvailableItemListUseCase.execute()
                .catch {
                    _ScreenHomeModel.value = ScreenHomeModel(
                        isLoading = false,
                        error = it,
                        availableAvailableItemList = emptyList()
                    )
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _ScreenHomeModel.value = ScreenHomeModel(
                                isLoading = false,
                                error = null,
                                availableAvailableItemList = result.data
                            )
                        }

                        is Result.Error -> {
                            _ScreenHomeModel.value = ScreenHomeModel(
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
                    _ScreenHomeModel.value = _ScreenHomeModel.value.copy(
                        cart = OrderWithItems(Order(-1), emptyList())
                    )
                }
                .collect { result ->
                    when(result){
                        is Result.Success -> {
                            _ScreenHomeModel.value = _ScreenHomeModel.value.copy(
                                cart = result.data
                            )
                        }
                        is Result.Error -> {
                            _ScreenHomeModel.value = _ScreenHomeModel.value.copy(
                                cart = OrderWithItems(Order(-1), emptyList())
                            )
                        }
                    }
                }
        }
    }

    override fun addToCart(availableItem: AvailableItem, quantity: Long) {
       viewModelScope.launch(Dispatchers.IO) {
           addToCartUseCase.execute(availableItem, quantity)
       }
    }
}