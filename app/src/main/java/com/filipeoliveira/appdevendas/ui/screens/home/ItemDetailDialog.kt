package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.ui.components.ItemLayoutForDialog
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal

@Composable
fun ItemDetailDialog(
    availableItem: AvailableItem,
    onDismiss: () -> Unit,
    onAddItemClicked: () -> Unit,
    onRemoveItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimen8Dp),
            shape = RoundedCornerShape(dimen16Dp),
        ) {
            ItemLayoutForDialog(
                availableItem = availableItem,
                modifier = modifier,
                onAddItemClicked = { onAddItemClicked() },
                onRemoveItemClicked = { onRemoveItemClicked() }
            )
        }
    }
}
@Preview(heightDp = 350)
@Composable
fun ItemDetailDialogPreview() {
    val availableItem = AvailableItem(
        name = "Carrinho de controle remoto",
        description = "Carrinho controlado por controle sem fio via Wifi. " +
                "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
        value = BigDecimal.valueOf(307.74),
        imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg",
        sku = "1"
    )

    ItemDetailDialog(availableItem, {}, {}, {})
}