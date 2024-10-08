package com.compose.tips.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnlineIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(60.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = .5f), CircleShape)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary, CircleShape),
    )
}