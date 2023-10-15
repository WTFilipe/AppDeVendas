package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.domain.errors.EmptyResponseError
import com.filipeoliveira.appdevendas.ui.components.CartResume
import com.filipeoliveira.appdevendas.ui.components.Loading
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import java.math.BigDecimal

@Composable
fun ScreenHome(
    onGoToCartClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScreenContent(
            onGoToCartClicked = { onGoToCartClicked()}
        )
    }
}

@Composable
private fun ScreenContent(
    onGoToCartClicked: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: ScreenHomeViewModelImpl = hiltViewModel()
) {
    val uiState = homeViewModel.screenHomeModel.collectAsState().value

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        when {
            uiState.isLoading -> {
                Loading()
            }
            uiState.error != null ->{
                OnError(uiState.error)
            }
            uiState.availableAvailableItemList.isNotEmpty() -> {
                OnAvailableItemsSuccess(
                    data = uiState,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1F),
                    onAddToCart = { item, selectedQuantity ->
                        homeViewModel.addToCart(item, selectedQuantity)
                    }
                )
            }
        }

        if (uiState.cartSize > 0){
            CartResume(
                itemQuantity = uiState.cartSize,
                totalPrice = uiState.cartValue,
                buttonText = stringResource(R.string.label_open_cart),
                onButtonClick = {
                    onGoToCartClicked()
                }
            )
        }
    }
}

@Composable
fun OnAvailableItemsSuccess(
    data: ScreenHomeModel,
    onAddToCart: (AvailableItem, Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDetailDialog by rememberSaveable {
        mutableStateOf<AvailableItem?>(null)
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimen16Dp),
    ) {
        val availableItems = data.availableAvailableItemList
        items(availableItems.size) {
            ItemLayoutForList(
                availableItem = availableItems[it],
                onItemClicked = { item ->
                    showDetailDialog = item
                },
                onAddToCardClicked = { item ->
                    onAddToCart(item, 1)
                }
            )
            if (it != availableItems.size - 1){
                Spacer(modifier = Modifier.height(dimen16Dp))
            }
        }
    }

    showDetailDialog?.let {
        val alreadySelectedQuantity = data.getItemSelectedQuantity(it.sku)

        ItemDetailDialog(
            availableItem = it,
            onDismiss = { item, selectedQuantity ->
                showDetailDialog = null
                onAddToCart(item,  selectedQuantity)
            },
            selectedQuantity = alreadySelectedQuantity
        )
    }
}

@Composable
fun OnError(error: Throwable, modifier: Modifier = Modifier) {
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
fun OnAvailableItemsSuccessPreview() {
    val fakeList = List(10) {
        AvailableItem(
            name = "Carrinho de controle remoto",
            description = "Carrinho controlado por controle sem fio via Wifi. " +
                    "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
            value = BigDecimal.valueOf(307.74),
            imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg",
            sku = "1234"
        )
    }

    val screenHomeModel = ScreenHomeModel(availableAvailableItemList = fakeList)
    OnAvailableItemsSuccess(data = screenHomeModel, onAddToCart = { _, _ -> })
}
