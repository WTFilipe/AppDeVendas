package com.filipeoliveira.appdevendas.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipeoliveira.appdevendas.data.model.OrderItem
import java.math.BigDecimal
import java.math.RoundingMode

@Entity
data class OrderItemDB(
    @PrimaryKey val sku: String,
    @ColumnInfo val quantityOfItems: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val imageURL: String,
    @ColumnInfo val valuePerItem: String,
    @ColumnInfo val orderId: Long
) {
    val totalValue: BigDecimal
        get() = quantityOfItems.toBigDecimal() * valuePerItem.toBigDecimal()

    fun toOrderItem() = OrderItem(
        sku = sku,
        quantityOfItems = quantityOfItems,
        name = name,
        description = description,
        imageURL = imageURL,
        valuePerItem = BigDecimal(valuePerItem).setScale(2, RoundingMode.FLOOR),
        orderId = orderId
    )
}