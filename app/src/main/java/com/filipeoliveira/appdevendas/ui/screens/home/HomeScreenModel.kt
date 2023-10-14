package com.filipeoliveira.appdevendas.ui.screens.home

import com.filipeoliveira.appdevendas.data.model.AvailableItem
import java.math.BigDecimal

data class HomeScreenModel(
    val isLoading: Boolean = false,
    val availableAvailableItemList: List<AvailableItem> = emptyList(),
    val error: Throwable? = null,
    val cartItemQuantity: Long = 0L,
    val cartPrice: BigDecimal = BigDecimal.ZERO
)