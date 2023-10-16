package com.filipeoliveira.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderDB (
    @PrimaryKey val orderId: Long,
) {
}