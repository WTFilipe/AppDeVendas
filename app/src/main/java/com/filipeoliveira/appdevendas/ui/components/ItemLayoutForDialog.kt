package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen30Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal

@Composable
fun ItemLayoutForDialog(
    availableItem: AvailableItem,
    modifier: Modifier = Modifier,
    onAddItemClicked: (() -> Unit),
    onRemoveItemClicked: (() -> Unit),
) {
    var selectedQuantity by rememberSaveable {
        mutableIntStateOf(0)
    }
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
            ItemLayoutTop(availableItem)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = dimen16Dp)
            ) {
                ItemLayoutBottomLeft(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .clickable {
                            selectedQuantity += 1
                        }
                        .padding(start = dimen16Dp)
                )
                if (selectedQuantity > 0){
                    ItemLayoutBottomRight(
                        selectedQuantity = selectedQuantity,
                        onRemoveItemClicked = {
                            selectedQuantity -= 1
                            onRemoveItemClicked()
                        },
                        onAddItemClicked = {
                            selectedQuantity += 1
                            onAddItemClicked()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)
                            .padding(end = dimen30Dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemLayoutBottomLeft(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant),
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

@Composable
private fun ItemLayoutBottomRight(
    selectedQuantity: Int,
    onAddItemClicked: () -> Unit,
    onRemoveItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimen8Dp, Alignment.End)
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

@Preview(name = "Layout for List", heightDp = 300)
@Composable
fun LayoutForDialogPreview() {
    ItemLayoutForDialog(availableItem = availableItem, onAddItemClicked =  {}, onRemoveItemClicked =  {})
}

private val availableItem = AvailableItem(
    name = "Carrinho de controle remoto",
    description = "Carrinho controlado por controle sem fio via Wifi. " +
            "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
    value = BigDecimal.valueOf(307.74),
    imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg",
    sku = "1"
)