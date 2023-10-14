package com.filipeoliveira.appdevendas.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipeoliveira.appdevendas.data.model.Order
import java.math.BigDecimal

@Entity
data class OrderDB (
    @PrimaryKey val orderId: Long,
    @ColumnInfo val isStillInCart: Boolean
) {
    fun toOrder() = Order(orderId = orderId)

    companion object {
        const val CART_ORDER_ID = -1L
    }
}