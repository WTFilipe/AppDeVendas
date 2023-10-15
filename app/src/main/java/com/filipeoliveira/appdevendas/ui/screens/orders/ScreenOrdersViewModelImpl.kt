package com.filipeoliveira.appdevendas.ui.screens.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.appdevendas.domain.GetOrdersListUseCase
import com.filipeoliveira.appdevendas.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenOrdersViewModelImpl @Inject constructor(
    private val getOrdersListUseCase: GetOrdersListUseCase,
) : ViewModel(), ScreenOrdersViewModel {

    private var _screenOrdersModel = MutableStateFlow(
        ScreenOrdersModel(
            isLoading = true,
            error = null,
            orders = emptyList()
        )
    )
    val screenOrdersModel: StateFlow<ScreenOrdersModel>
        get() = _screenOrdersModel

    init {
        this.loadOrders()
    }
    override fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            getOrdersListUseCase.execute()
                .catch {
                    _screenOrdersModel.value = _screenOrdersModel.value.copy(
                        isLoading = true,
                        error = it,
                        orders = emptyList()
                    )
                }
                .collect { result ->
                    when(result){
                        is Result.Success -> {
                            _screenOrdersModel.value = _screenOrdersModel.value.copy(
                                isLoading = false,
                                error = null,
                                orders = result.data
                            )
                        }
                        is Result.Error -> {
                            _screenOrdersModel.value = _screenOrdersModel.value.copy(
                                isLoading = false,
                                error = result.error,
                                orders = emptyList()
                            )
                        }
                    }
                }
        }
    }

}