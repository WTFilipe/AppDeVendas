package com.filipeoliveira.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class CartItemDB(
    @PrimaryKey val sku: String,
    @ColumnInfo val quantityOfItems: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val imageURL: String,
    @ColumnInfo val valuePerItem: String,
    @ColumnInfo val orderId: Long
) {
    companion object {
        const val CART_ORDER_ID = -1L
    }
}