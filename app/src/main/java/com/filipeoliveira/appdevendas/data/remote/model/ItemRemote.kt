package com.filipeoliveira.appdevendas.data.remote.model

import com.filipeoliveira.appdevendas.data.model.Item
import java.math.BigDecimal

data class ItemRemote(
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
) {
    fun toItem() : Item = Item(name, description, value, imageURL)
}