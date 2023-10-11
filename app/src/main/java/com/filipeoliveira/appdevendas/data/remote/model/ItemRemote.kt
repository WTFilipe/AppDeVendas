package com.filipeoliveira.appdevendas.data.remote.model

import java.math.BigDecimal

data class ItemRemote(
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
)
