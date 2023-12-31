package com.filipeoliveira.appdevendas.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.domain.model.Order
import com.filipeoliveira.domain.model.OrderWithItems
import com.filipeoliveira.domain.FinishPurchaseUseCase
import com.filipeoliveira.domain.GetCartUseCase
import com.filipeoliveira.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenCartViewModelImpl @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val finishPurchaseUseCase: FinishPurchaseUseCase
) : ViewModel(), ScreenCartViewModel {

    private var _screenCartModel = MutableStateFlow(
        ScreenCartModel(
            isLoading = true,
            error = null,
            cart = OrderWithItems(Order(-1), emptyList())
        )
    )
    val screenCartModel: StateFlow<ScreenCartModel>
        get() = _screenCartModel

    init {
        this.getCart()
    }

    override fun getCart() {
        viewModelScope.launch(Dispatchers.IO) {
            getCartUseCase.execute()
                .catch {
                    _screenCartModel.value = _screenCartModel.value.copy(
                        isLoading = false,
                        cart = OrderWithItems(Order(-1), emptyList()),
                        error = it
                    )
                }
                .collect { result ->
                    if (result is Result.Success) {
                        _screenCartModel.value = _screenCartModel.value.copy(
                            cart = result.data
                        )
                    }
                    when (result) {
                        is Result.Success -> {
                            _screenCartModel.value = _screenCartModel.value.copy(
                                cart = result.data,
                                error = null,
                                isLoading = false
                            )
                        }

                        is Result.Error -> {
                            _screenCartModel.value = _screenCartModel.value.copy(
                                cart = OrderWithItems(Order(-1), emptyList()),
                                error = result.error,
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
    override fun finishPurchase(orderWithItems: OrderWithItems) {
        viewModelScope.launch(Dispatchers.IO) {
            finishPurchaseUseCase.execute(orderWithItems)
        }
    }

}