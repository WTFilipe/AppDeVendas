package com.filipeoliveira.appdevendas.data.remote.model

import com.filipeoliveira.domain.model.AvailableItem
import java.math.BigDecimal

data class ItemRemote(
    val sku: String,
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageURL: String
) {
    fun toItem() : AvailableItem = AvailableItem(sku = sku, name = name, description =  description, value =  value, imageURL =  imageURL)
}