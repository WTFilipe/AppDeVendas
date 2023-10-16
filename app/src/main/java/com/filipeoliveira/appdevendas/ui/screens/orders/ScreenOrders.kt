package com.filipeoliveira.appdevendas.ui.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.domain.model.Order
import com.filipeoliveira.domain.model.OrderItem
import com.filipeoliveira.domain.model.OrderWithItems
import com.filipeoliveira.domain.errors.EmptyResponseError
import com.filipeoliveira.appdevendas.ui.components.Loading
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun ScreenOrders(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScreenContent()
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    screenOrdersViewModelImpl: ScreenOrdersViewModelImpl = hiltViewModel()
) {
    val uiState = screenOrdersViewModelImpl.screenOrdersModel.collectAsState().value

    Surface(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        when{
            uiState.isLoading -> {
                Loading(modifier.fillMaxSize())
            }
            uiState.error != null ->{
                OnError(uiState.error)
            }
            uiState.orders.isNotEmpty() ->{
                OnGetOrdersSuccess(uiState.orders)
            }
        }
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

@Composable
fun OnGetOrdersSuccess(orders: List<OrderWithItems>) {
    LazyColumn (
        contentPadding = PaddingValues(dimen16Dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ){
        items(orders.size){
            FinishedOrderItemLayout(orders[it])
            if (it != orders.size - 1){
                Spacer(modifier = Modifier.height(dimen16Dp))
            }
        }
    }
}

@Preview
@Composable
fun OnGetOrdersSuccessPreview(){
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
    val orderWithItems = OrderWithItems(Order(1234L), items = items)
    OnGetOrdersSuccess(
        List(3){
            orderWithItems
        }
    )
}