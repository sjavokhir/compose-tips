package com.compose.tips.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun TypingAnimationSample(text: String) {
    val completedText by remember(text) { mutableStateOf(text) }
    var displayedText by remember { mutableStateOf("") }
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(completedText) {
        val textLength = completedText.length

        while (currentIndex < textLength) {
            currentIndex = minOf(currentIndex + 1, textLength)
            displayedText = completedText.substring(startIndex = 0, endIndex = currentIndex)
            delay(40)
        }
    }

    Text(text = displayedText)
}