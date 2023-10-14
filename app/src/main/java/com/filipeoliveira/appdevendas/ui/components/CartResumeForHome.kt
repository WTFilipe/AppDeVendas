package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.ui.dimen16Dp
import com.filipeoliveira.appdevendas.ui.dimen8Dp
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun CartResumeForHome(
    itemQuantity: Long,
    totalPrice: BigDecimal,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .height(IntrinsicSize.Max)
            .padding(horizontal = dimen16Dp)
    ) {
        CartResumeLeft(
            itemQuantity,
            totalPrice,
            Modifier
                .fillMaxHeight()
                .weight(1F)
        )
        CartResumeRight(
            {},
            Modifier
                .fillMaxHeight()
                .weight(1F)
        )
    }
}
@Composable
private fun CartResumeLeft(
    itemQuantity: Long,
    totalPrice: BigDecimal,
    modifier: Modifier = Modifier)
{
    Column(
        modifier = modifier
            .padding(vertical = dimen16Dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(stringResource(R.string.label_order_items_quantity, itemQuantity))
        Spacer(Modifier.height(dimen8Dp))
        Text(
            stringResource(
                R.string.label_order_total_price,
                totalPrice.setScale(2, RoundingMode.UNNECESSARY)
            ))
    }
}
@Composable
private fun CartResumeRight(onButtonClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Button(
            onClick = { onButtonClick () },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ){
            Text(
                text = stringResource(R.string.label_open_cart),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview (heightDp = 75)
@Composable
fun CartResumeForHomePreview(){
    CartResumeForHome(
        10L, BigDecimal(99.99)
    )
}