package com.spacex.rockets.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spacex.rockets.R

@Composable
fun ErrorComponent(
    reason: Exception, // TODO use reason to give more actionable error message
    retry: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp)) {
        Text(
            text = stringResource(id = R.string.error_title),
            style = MaterialTheme.typography.titleMedium
        )
        Icon(painterResource(id = R.drawable.ic_rocket_crashed), contentDescription = null)
        TextButton(onClick = retry) {
            Text(text = stringResource(id = R.string.error_button))
        }
    }
}