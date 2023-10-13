package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.appdevendas.domain.GetAvailableItemListUseCase
import com.filipeoliveira.appdevendas.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModelImpl @Inject constructor (
    private val getAvailableItemListUseCase: GetAvailableItemListUseCase
) : ViewModel(), HomeViewModel {

    private var _homeScreenModel = MutableStateFlow(
        HomeScreenModel(
            isLoading = true,
            error = null,
            availableItemList = emptyList()
        )
    )
    val homeScreenModel: StateFlow<HomeScreenModel>
        get() = _homeScreenModel

    init {
        this.loadAvailableItems()
    }
    override fun loadAvailableItems() {
        _homeScreenModel = MutableStateFlow(
            HomeScreenModel(
                isLoading = true,
                error = null,
                availableItemList = emptyList()
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            getAvailableItemListUseCase.execute()
                .catch {
                    _homeScreenModel = MutableStateFlow(
                        HomeScreenModel(
                            isLoading = false,
                            error = it,
                            availableItemList = emptyList()
                        )
                    )
                }
                .collect{ result ->
                    when(result){
                        is Result.Success -> {
                            _homeScreenModel = MutableStateFlow(
                                HomeScreenModel(
                                    isLoading = false,
                                    error = null,
                                    availableItemList = result.data
                                )
                            )
                        }
                        is Result.Error -> {
                            _homeScreenModel = MutableStateFlow(
                                HomeScreenModel(
                                    isLoading = false,
                                    error = result.error,
                                    availableItemList = emptyList()
                                )
                            )
                        }
                    }
                }
        }
    }
}