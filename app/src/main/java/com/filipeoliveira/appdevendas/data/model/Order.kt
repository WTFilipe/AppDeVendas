package com.filipeoliveira.appdevendas.data.model

import java.math.BigDecimal

data class Order (
    val orderId: Long,
    val quantityOfItems: Long,
    val orderValue: BigDecimal
)