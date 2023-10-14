package com.filipeoliveira.appdevendas.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.appdevendas.data.model.AvailableItem
import com.filipeoliveira.appdevendas.ui.components.CartResumeForHome
import com.filipeoliveira.appdevendas.ui.components.ItemLayoutForList
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import java.math.BigDecimal

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScreenContent()
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModelImpl = hiltViewModel()
) {
    val uiState = homeViewModel.homeScreenModel.collectAsState().value

    Column(modifier = modifier.fillMaxSize()) {

        if (uiState.availableAvailableItemList.isNotEmpty()) {
            OnAvailableItemsSuccess(
                data = uiState,
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1F),
                onAddToCart = { item, selectedQuantity ->
                    homeViewModel.addToCard(item, selectedQuantity)
                }
            )
        }

        if (uiState.cartSize > 0){
            CartResumeForHome(
                uiState.cartSize,
                uiState.cartValue
            )
        }
    }
}

@Composable
fun OnAvailableItemsSuccess(
    data: HomeScreenModel,
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
            Spacer(modifier = Modifier.height(dimen16Dp))
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

    val homeScreenModel = HomeScreenModel(availableAvailableItemList = fakeList)
    OnAvailableItemsSuccess(data = homeScreenModel, onAddToCart = {_,_ -> })
}
