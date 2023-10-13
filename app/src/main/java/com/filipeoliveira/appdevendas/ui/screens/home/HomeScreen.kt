package com.filipeoliveira.appdevendas.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.appdevendas.data.model.Item
import com.filipeoliveira.appdevendas.ui.components.ItemLayoutForList
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import java.math.BigDecimal

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModelImpl = hiltViewModel()
){
    val uiState = homeViewModel.homeScreenModel.collectAsState().value

    Surface(
        modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        when{
            uiState.availableItemList.isNotEmpty() -> OnAvailableItemsSuccess(uiState.availableItemList)
            else -> {
                Box {
                    Toast.makeText(LocalContext.current, "Toast", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun OnAvailableItemsSuccess(data: List<Item>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(dimen16Dp),
    ) {
        items(data.size){
            ItemLayoutForList(
                item = data[it]
            )
            Spacer(modifier = Modifier.height(dimen16Dp))
        }
    }
}
@Preview
@Composable
fun OnAvailableItemsSuccessPreview() {
    val fakeList = List(10){
        Item(
            name = "Carrinho de controle remoto",
            description = "Carrinho controlado por controle sem fio via Wifi. " +
                    "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
            value = BigDecimal.valueOf(307.74),
            imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg"
        )
    }
    OnAvailableItemsSuccess(data = fakeList)
}
