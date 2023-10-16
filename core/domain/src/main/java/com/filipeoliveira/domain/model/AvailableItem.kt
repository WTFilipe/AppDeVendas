package com.filipeoliveira.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

data class AvailableItem(
    val sku: String,
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
) {

}