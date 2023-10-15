package com.filipeoliveira.appdevendas.data.model

import com.filipeoliveira.appdevendas.data.local.model.OrderDB


data class Order (
    val orderId: Long
) {
    fun toOrderDB() = OrderDB(orderId = orderId, isStillInCart = false)
}