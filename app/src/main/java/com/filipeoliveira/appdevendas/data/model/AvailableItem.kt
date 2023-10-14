package com.filipeoliveira.appdevendas.data.model

import java.math.BigDecimal

data class AvailableItem(
    val sku: String,
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
)