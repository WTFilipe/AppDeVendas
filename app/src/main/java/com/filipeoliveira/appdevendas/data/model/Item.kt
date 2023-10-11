package com.filipeoliveira.appdevendas.data.model

import java.math.BigDecimal

data class Item(
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
)