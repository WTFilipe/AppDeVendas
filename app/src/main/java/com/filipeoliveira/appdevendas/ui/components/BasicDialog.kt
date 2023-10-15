package com.filipeoliveira.appdevendas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.filipeoliveira.appdevendas.R
import com.filipeoliveira.appdevendas.ui.dimen16Dp

@Composable
fun BasicDialog(
    text: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card (
            modifier = modifier
        ){
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(start = dimen16Dp, end = dimen16Dp, top = dimen16Dp)
            ) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(dimen16Dp))
                Row {
                    TextButton(
                        onClick = { onDismiss() }
                    ) {
                        Text(
                            text = stringResource(R.string.label_no),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    TextButton(
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.label_yes),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BasicDialogPreview() {
    BasicDialog("Are you sure?", {}, {})
}