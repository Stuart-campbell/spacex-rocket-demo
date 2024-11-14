@file:OptIn(ExperimentalLayoutApi::class)

package com.spacex.rockets.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spacex.rockets.R

enum class VisualMetric(val value: Double) {
    Weight(17000.0),
    Height(3.2)
}

@Composable
fun VisualScale(
    metric: VisualMetric,
    value: Double
) {

    FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        val full = (value / metric.value).toInt()
        val part: Float = ((value % metric.value) / metric.value).toFloat()

        val painter = when(metric) {
            VisualMetric.Weight -> painterResource(id = R.drawable.ic_bus)
            VisualMetric.Height -> painterResource(id = R.drawable.ic_elephant)
        }

        (0..full).forEach { _ ->
            Icon(
                painter,
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        }

        Icon(
            painter,
            modifier = Modifier
                .size(24.dp)
                .drawWithContent {
                    clipRect(right = size.width * part) {
                        this@drawWithContent.drawContent()
                    }
                },
            contentDescription = null
        )

    }

}