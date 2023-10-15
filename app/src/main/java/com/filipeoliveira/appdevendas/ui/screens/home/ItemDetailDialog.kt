package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal

@Composable
fun ItemDetailDialog(
    availableItem: AvailableItem,
    onDismiss: (AvailableItem, Long) -> Unit,
    modifier: Modifier = Modifier,
    selectedQuantity: Long = 0
) {
    var selectedQuantity by rememberSaveable {
        mutableLongStateOf(selectedQuantity)
    }

    Dialog(
        onDismissRequest = { onDismiss(availableItem, selectedQuantity) },
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
                onAddItemClicked = { selectedQuantity += 1 },
                onRemoveItemClicked = { selectedQuantity -= 1 },
                selectedQuantity = selectedQuantity
            )
        }
    }
}
@Preview(heightDp = 350)
@Composable
fun ItemDetailDialogPreview() {
    ItemDetailDialog(availableItem, { _, _ -> })
}