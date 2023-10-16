package com.filipeoliveira.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipeoliveira.domain.model.OrderItem
import java.math.BigDecimal
import java.math.RoundingMode

@Entity
data class OrderItemDB(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo val sku: String,
    @ColumnInfo val quantityOfItems: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val imageURL: String,
    @ColumnInfo val valuePerItem: String,
    @ColumnInfo val orderId: Long
) {
    val totalValue: BigDecimal
        get() = quantityOfItems.toBigDecimal() * valuePerItem.toBigDecimal()
}