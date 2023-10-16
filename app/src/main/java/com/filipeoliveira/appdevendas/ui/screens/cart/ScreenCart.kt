package com.filipeoliveira.appdevendas.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.data.model.Order
import com.filipeoliveira.appdevendas.data.model.OrderItem
import com.filipeoliveira.appdevendas.data.model.OrderWithItems
import com.filipeoliveira.appdevendas.domain.errors.EmptyResponseError
import com.filipeoliveira.appdevendas.ui.components.BasicDialog
import com.filipeoliveira.appdevendas.ui.components.CartResume
import com.filipeoliveira.appdevendas.ui.components.Loading
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun ScreenCart(
    modifier: Modifier = Modifier
){
    Surface (
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ){
        ScreenContent(modifier)
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    screenCartViewModel: ScreenCartViewModelImpl = hiltViewModel()
) {
    val uiState = screenCartViewModel.screenCartModel.collectAsState().value
    var showDialog: Boolean by rememberSaveable{
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(top = dimen16Dp)
    ) {
        val cartItems = uiState.cart.items

        if (uiState.isLoading){
            Loading()
        }

        if (uiState.error != null){
            OnCartError(uiState.error)
        }

        if (cartItems.isNotEmpty()){
            OnGetCartSuccess(uiState.cart, modifier.weight(1F))
            CartResume(
                itemQuantity = uiState.cart.quantityOfItems,
                totalPrice = uiState.cart.orderValue,
                buttonText = stringResource(R.string.label_finish_purchase),
                onButtonClick = {
                    showDialog = true
                }
            )
        }

        if (showDialog){
            BasicDialog(
                text = stringResource(R.string.feedback_confirm_purchase),
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    screenCartViewModel.finishPurchase(orderWithItems = uiState.cart)
                }
            )
        }
    }
}

@Composable
private fun OnGetCartSuccess(
    orderWithItems: OrderWithItems,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = dimen16Dp)
    ) {
        val items = orderWithItems.items
        items(items.size) {
            Card {
                CartItemLayout(items[it])
            }
            Spacer(modifier = Modifier.height(dimen8Dp))
        }
    }
}

@Composable
fun OnCartError(error: Throwable, modifier: Modifier = Modifier) {
    val errorText = when(error){
        is EmptyResponseError -> stringResource(R.string.error_empty_response)
        else -> stringResource(R.string.error_generic)
    }

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ){
        Text(
            text = errorText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Preview
@Composable
fun OnGetCartSuccessPreview() {
    val items = List(3){
        OrderItem(
            name = "Pista Hot Wheels - Parque dos Tubarões",
            description = "Desafie o Mega Tubarão e passe por dentro de sua boca, mas seja rápido o suficiente para não levar uma dentada!",
            imageURL = "https://photos.enjoei.com.br/parque-dos-tubaroes/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy80ODc4ODYvNjg1YTUyYmVjYmUwNDM4MmI2OTA5OWM0NWRkZWFkOGUuanBn",
            sku = "2",
            quantityOfItems = 3,
            orderId = -1,
            valuePerItem = BigDecimal(307.74).setScale(2, RoundingMode.FLOOR)
        )
    }
    val orderWithItems = OrderWithItems(Order(1L), items = items)
    OnGetCartSuccess(orderWithItems)
}