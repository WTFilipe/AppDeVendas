package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.data.model.Item
import com.filipeoliveira.appdevendas.ui.dimen12Dp
import com.filipeoliveira.appdevendas.ui.dimen150Dp
import com.filipeoliveira.appdevendas.ui.dimen20Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal

@Composable
fun ItemLayoutForList(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen8Dp
        )
    ) {
        Column(
            modifier = Modifier
        ) {
            ItemLayoutTop(item)
            ItemLayoutBottom()
        }
    }
}

@Composable
private fun ItemLayoutTop(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ItemLayoutTopLeft(item)
        ItemLayoutTopRight(item)
    }
}

@Composable
private fun ItemLayoutTopLeft(item: Item) {
    AsyncImage(
        model = item.imageURL,
        contentDescription = null,
        modifier = Modifier
            .size(dimen150Dp)
    )
}

@Composable
private fun ItemLayoutTopRight(item: Item, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(dimen150Dp)
            .padding(vertical = dimen8Dp, horizontal = dimen8Dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.name,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = item.description,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(dimen20Dp))
        Text(
            text = item.value.toString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ItemLayoutBottom(modifier: Modifier = Modifier) {
    var selectedQuantity by rememberSaveable {
        mutableIntStateOf(0)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimen12Dp)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemLayoutBottomLeft(
            onAddToCartClicked = { selectedQuantity += 1 },
            modifier = Modifier.weight(1F)
        )
        if (selectedQuantity > 0){
            ItemLayoutBottomRight(
                modifier = Modifier.weight(1F),
                selectedQuantity = selectedQuantity,
                onAddItemClicked = { selectedQuantity += 1 },
                onRemoveItemClicked = { selectedQuantity -= 1 }
            )
        }
    }

}

@Composable
private fun ItemLayoutBottomLeft(
    onAddToCartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                onAddToCartClicked()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Adicionar ao carrinho",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ItemLayoutBottomRight(
    modifier: Modifier = Modifier,
    selectedQuantity: Int,
    onAddItemClicked: () -> Unit,
    onRemoveItemClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimen8Dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_minus_circle),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.clickable {
                onRemoveItemClicked()
            }
        )
        Text(
            text = selectedQuantity.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Icon(
            painter = painterResource(R.drawable.icon_add_circle),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.clickable {
                onAddItemClicked()
            }
        )
    }

}

@Preview(heightDp = 250)
@Composable
fun ItemLayoutForListPreview() {
    ItemLayoutForList(
        Item(
            name = "Carrinho de controle remoto",
            description = "Carrinho controlado por controle sem fio via Wifi. " +
                    "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
            value = BigDecimal.valueOf(307.74),
            imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg"
        )
    )
}