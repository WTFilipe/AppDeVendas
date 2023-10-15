package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.ui.dimen100Dp

@Composable
fun Loading(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(dimen100Dp)
            )
            Text(
                text = stringResource(R.string.label_loading),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}