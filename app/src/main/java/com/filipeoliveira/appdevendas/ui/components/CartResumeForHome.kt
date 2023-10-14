package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.math.BigDecimal

@Composable
fun CartResumeForHome(
    itemQuantity: Long,
    totalPrice: BigDecimal,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        CartResumeLeft()
        CartResumeRight()
    }
}
@Composable
private fun CartResumeLeft() {
    Column {

    }
}
@Composable
private fun CartResumeRight() {
    Column {

    }
}

@Preview
@Composable
fun CartResumeForHomePreview(){
    CartResumeForHome(
        10L, BigDecimal(99.99)
    )
}