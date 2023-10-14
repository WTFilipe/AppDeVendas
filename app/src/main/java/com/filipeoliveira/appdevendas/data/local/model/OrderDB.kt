package com.filipeoliveira.appdevendas.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipeoliveira.appdevendas.data.model.Order
import java.math.BigDecimal

@Entity
data class OrderDB (
    @PrimaryKey val orderId: Long,
    @ColumnInfo val quantityOfItems: Long,
    @ColumnInfo val orderValue: String,
    @ColumnInfo val isStillInCart: Boolean
) {
    fun toOrder() = Order(orderId = orderId, quantityOfItems = quantityOfItems, orderValue = BigDecimal(orderValue))
}