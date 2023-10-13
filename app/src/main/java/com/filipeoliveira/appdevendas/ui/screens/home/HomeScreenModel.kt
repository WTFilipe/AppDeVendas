package com.filipeoliveira.appdevendas.ui.screens.home

import com.filipeoliveira.appdevendas.data.model.Item

data class HomeScreenModel(
    val isLoading: Boolean = false,
    val availableItemList: List<Item> = emptyList(),
    val error: Throwable? = null
)