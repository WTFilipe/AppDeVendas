package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.filipeoliveira.appdevendas.data.model.Item
import com.filipeoliveira.appdevendas.ui.dimen12Dp
import com.filipeoliveira.appdevendas.ui.dimen150Dp
import com.filipeoliveira.appdevendas.ui.dimen20Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal

@Composable
fun ItemLayoutForList(
    item: Item,
    modifier: Modifier = Modifier,
    onItemClicked: ((Item) -> Unit),
    onAddToCardClicked: ((Item) -> Unit),
) {
    Card(
        modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable {
                onItemClicked(item)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen8Dp
        )
    ) {
        Column(
            modifier = Modifier
        ) {
            ItemLayoutTop(item, titleMaxLines = 2, subtitleMaxLines = 2)
            ItemLayoutBottom(modifier = modifier.clickable { onAddToCardClicked(item) })
        }
    }
}

@Composable
fun ItemLayoutTop(
    item: Item,
    titleMaxLines: Int = Int.MAX_VALUE,
    subtitleMaxLines: Int = Int.MAX_VALUE
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ItemLayoutTopLeft(item)
        ItemLayoutTopRight(
            item = item,
            modifier = Modifier.height(IntrinsicSize.Max),
            titleMaxLines = titleMaxLines,
            subtitleMaxLines = subtitleMaxLines
        )
    }
}

@Composable
fun ItemLayoutTopLeft(item: Item) {
    AsyncImage(
        model = item.imageURL,
        contentDescription = null,
        modifier = Modifier
            .size(dimen150Dp)
    )
}

@Composable
fun ItemLayoutTopRight(
    item: Item,
    modifier: Modifier = Modifier,
    titleMaxLines: Int = Int.MAX_VALUE,
    subtitleMaxLines: Int = Int.MAX_VALUE
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimen8Dp, horizontal = dimen8Dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.name,
            modifier = Modifier.fillMaxWidth(),
            maxLines = titleMaxLines,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(dimen8Dp))
        Text(
            text = item.description,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = subtitleMaxLines,
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
fun ItemLayoutBottom(
    modifier: Modifier = Modifier,
    onAddToCartClicked: (() -> Unit)? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimen12Dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onAddToCartClicked?.let { it() }
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Adicionar ao carrinho",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }

}

@Preview(name = "Layout for List", heightDp = 300)
@Composable
fun LayoutForListPreview() {
    ItemLayoutForList(item = item, onItemClicked = {}, onAddToCardClicked =  {})
}

private val item = Item(
    name = "Carrinho de controle remoto",
    description = "Carrinho controlado por controle sem fio via Wifi. " +
            "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
    value = BigDecimal.valueOf(307.74),
    imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg"
)