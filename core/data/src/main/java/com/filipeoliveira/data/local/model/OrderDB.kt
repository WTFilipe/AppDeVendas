package com.filipeoliveira.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderDB (
    @PrimaryKey val orderId: Long,
    @ColumnInfo val isStillInCart: Boolean
) {
    companion object {
        const val CART_ORDER_ID = -1L
    }
}