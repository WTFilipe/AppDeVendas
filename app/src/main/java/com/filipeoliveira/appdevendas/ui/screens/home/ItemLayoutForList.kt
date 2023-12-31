package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.domain.model.AvailableItem
import com.filipeoliveira.appdevendas.ui.dimen150Dp
import com.filipeoliveira.appdevendas.ui.dimen20Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import com.filipeoliveira.appdevendas.ui.forwardingPainter
import java.math.BigDecimal

@Composable
fun ItemLayoutForList(
    availableItem: AvailableItem,
    modifier: Modifier = Modifier,
    onItemClicked: ((AvailableItem) -> Unit)
) {
    Card(
        modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(availableItem)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen8Dp
        )
    ) {
        ItemLayoutTop(availableItem, titleMaxLines = 2, subtitleMaxLines = 2)
    }
}

@Composable
fun ItemLayoutTop(
    availableItem: AvailableItem,
    titleMaxLines: Int = Int.MAX_VALUE,
    subtitleMaxLines: Int = Int.MAX_VALUE
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemLayoutTopLeft(availableItem)
        ItemLayoutTopRight(
            availableItem = availableItem,
            modifier = Modifier.fillMaxHeight(),
            titleMaxLines = titleMaxLines,
            subtitleMaxLines = subtitleMaxLines
        )
    }
}

@Composable
fun ItemLayoutTopLeft(availableItem: AvailableItem) {
    AsyncImage(
        model = availableItem.imageURL,
        contentDescription = null,
        modifier = Modifier
            .size(dimen150Dp),
        error = forwardingPainter(
            painter = painterResource(R.drawable.ic_android_24dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        ),
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun ItemLayoutTopRight(
    availableItem: AvailableItem,
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
            text = availableItem.name,
            modifier = Modifier.fillMaxWidth(),
            maxLines = titleMaxLines,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = availableItem.description,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = subtitleMaxLines,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(dimen20Dp))
        Text(
            text = stringResource(R.string.label_currency, availableItem.value),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(name = "Layout for List")
@Composable
fun LayoutForListPreview() {
    ItemLayoutForList(availableItem = availableItem, onItemClicked = {})
}

private val availableItem = AvailableItem(
    name = "Carrinho de controle remoto",
    description = "Carrinho controlado por controle sem fio via Wifi. " +
            "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
    value = BigDecimal.valueOf(307.74),
    imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg",
    sku = "1"
)