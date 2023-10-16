package com.filipeoliveira.appdevendas.ui.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.domain.model.Order
import com.filipeoliveira.domain.model.OrderItem
import com.filipeoliveira.domain.model.OrderWithItems
import com.filipeoliveira.appdevendas.ui.dimen12Dp
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen1dp
import com.filipeoliveira.appdevendas.ui.dimen2Dp
import com.filipeoliveira.appdevendas.ui.dimen42Dp
import com.filipeoliveira.appdevendas.ui.dimen4Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import com.filipeoliveira.appdevendas.ui.screens.cart.CartItemLayout
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun FinishedOrderItemLayout(
    orderWithItems: OrderWithItems,
    modifier: Modifier = Modifier
) {
    var showFullContent by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = dimen2Dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            FinishedOrderItemLayoutTop(
                orderWithItems = orderWithItems,
                onArrowClicked = {
                    showFullContent = !showFullContent
                },
                showFullContent = showFullContent
            )
            if (showFullContent){
                Spacer(modifier = Modifier.height(dimen8Dp))
                Spacer(modifier = Modifier.height(dimen1dp).fillMaxWidth().background(MaterialTheme.colorScheme.onSurface))
                FinishedOrderItemLayoutBottom(orderWithItems, showFullContent)
            }
        }
    }
}

@Composable
private fun FinishedOrderItemLayoutTop(
    orderWithItems: OrderWithItems,
    onArrowClicked: () -> Unit,
    showFullContent: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(bottom = dimen12Dp)
        .clickable {
            onArrowClicked()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = if (showFullContent) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimen16Dp, end = dimen42Dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_order_number),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)
            )

            Text(
                text = "#${orderWithItems.order.orderId}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimen16Dp, end = dimen42Dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_order_items_quantity, ""),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Text(
                text = "${orderWithItems.quantityOfItems}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimen16Dp, end = dimen42Dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_order_total_price, ""),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Text(
                text = stringResource(R.string.label_currency, orderWithItems.orderValue.setScale(2, RoundingMode.FLOOR)),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
        }
    }

}

@Composable
fun FinishedOrderItemLayoutBottom(
    orderWithItems: OrderWithItems,
    showDescription: Boolean,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier){
        for (item in orderWithItems.items){
            CartItemLayout(
                orderItem = item,
                showDescription = showDescription,
            )
            Spacer(modifier = Modifier.height(dimen4Dp))
        }
    }
}

@Preview(heightDp = 300)
@Composable
fun FinishedOrderItemLayoutPreview() {
    val orderWithItems = OrderWithItems(Order(12345L), List(3) {
        OrderItem(
            name = "Pista Hot Wheels - Parque dos Tubarões",
            description = "Desafie o Mega Tubarão e passe por dentro de sua boca, mas seja rápido o suficiente para não levar uma dentada!",
            imageURL = "https://photos.enjoei.com.br/parque-dos-tubaroes/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy80ODc4ODYvNjg1YTUyYmVjYmUwNDM4MmI2OTA5OWM0NWRkZWFkOGUuanBn",
            sku = "2",
            quantityOfItems = 3,
            orderId = -1,
            valuePerItem = BigDecimal(307.74).setScale(2, RoundingMode.FLOOR)
        )
    })
    FinishedOrderItemLayout(orderWithItems = orderWithItems)
}