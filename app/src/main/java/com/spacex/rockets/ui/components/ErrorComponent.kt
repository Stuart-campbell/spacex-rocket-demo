package com.spacex.rockets.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorComponent(
    reason: Exception,
    retry: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Oops Something went wrong!!")
        Button(onClick = retry) {
            Text(text = "Retry")
        }
    }
}