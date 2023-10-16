package com.filipeoliveira.appdevendas.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.data.model.OrderItem
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen1dp
import com.filipeoliveira.appdevendas.ui.dimen4Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import com.filipeoliveira.appdevendas.ui.forwardingPainter
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun CartItemLayout(
    orderItem: OrderItem,
    modifier: Modifier = Modifier,
    showDescription: Boolean = false
) {
    Column(
        modifier = modifier
            .background(Color.Transparent)
    ) {
        CartItemLayoutTop(orderItem, modifier, showDescription)
        Spacer(
            modifier = Modifier
                .height(dimen1dp)
                .background(MaterialTheme.colorScheme.onSurface)
                .fillMaxWidth()
        )
        CartItemLayoutBottom(orderItem.totalValue)
    }
}

@Composable
private fun CartItemLayoutTop(
    orderItem: OrderItem,
    modifier: Modifier = Modifier,
    showDescription: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(IntrinsicSize.Max)
    ) {
        CartItemLayoutTopLeft(orderItem, modifier.weight(1F))
        CartItemLayoutTopRight(orderItem, modifier.weight(2F), showDescription)
    }
}

@Composable
private fun CartItemLayoutTopLeft(orderItem: OrderItem, modifier: Modifier = Modifier) {
    val painter = forwardingPainter(
        painter = painterResource(R.drawable.ic_android_24dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
    )
    AsyncImage(
        model = orderItem.imageURL,
        contentDescription = null,
        modifier = modifier,
        error = painter,
        contentScale = ContentScale.Fit,
    )
}

@Composable
private fun CartItemLayoutTopRight(
    orderItem: OrderItem,
    modifier: Modifier = Modifier,
    showDescription: Boolean
) {
    Column(
        modifier = modifier
            .padding(start = dimen8Dp, end = dimen16Dp, top = dimen8Dp, bottom = dimen8Dp)
            .background(Color.Transparent)
    ) {
        Text(
            text = orderItem.name,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(dimen4Dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.label_value_per_unit),
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(R.string.label_currency, orderItem.valuePerItem.toString()),
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface

            )
        }

        Spacer(
            modifier = Modifier
                .height(dimen4Dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.label_quantity),
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = orderItem.quantityOfItems.toString(),
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(modifier = Modifier.height(dimen8Dp))

        if (showDescription){
            Text(
                text = orderItem.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CartItemLayoutBottom(
    totalValue: BigDecimal,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimen16Dp, vertical = dimen8Dp)
    ) {
        Text(
            text = stringResource(R.string.label_item_total_value),
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(R.string.label_currency, totalValue.toString()),
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun CartItemLayoutPreview() {
    val item = OrderItem(
        name = "Pista Hot Wheels - Parque dos Tubarões",
        description = "Desafie o Mega Tubarão e passe por dentro de sua boca, mas seja rápido o suficiente para não levar uma dentada!",
        imageURL = "https://photos.enjoei.com.br/parque-dos-tubaroes/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy80ODc4ODYvNjg1YTUyYmVjYmUwNDM4MmI2OTA5OWM0NWRkZWFkOGUuanBn",
        sku = "2",
        quantityOfItems = 3,
        orderId = -1,
        valuePerItem = BigDecimal(307.74).setScale(2, RoundingMode.FLOOR)
    )
    Card{
        CartItemLayout(item)
    }
}