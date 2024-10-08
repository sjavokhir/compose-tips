package com.compose.tips.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Popup

@Composable
fun PopupAnimationContent() {
    val state = remember {
        MutableTransitionState(false)
    }

    Column {
        Button(onClick = {
            state.targetState = true
        }) {
            Text(text = "Open")
        }
    }

    Popup(
        alignment = Alignment.BottomCenter,
        onDismissRequest = {
            state.targetState = false
        },
    ) {
        AnimatedVisibility(
            visibleState = state,
            enter = fadeIn() + slideInVertically(
                animationSpec = tween(400),
                initialOffsetY = { fullHeight -> fullHeight / 2 },
            ),
            exit = fadeOut(animationSpec = tween(200)) +
                    slideOutVertically(animationSpec = tween(400)),
            label = "poll more options dialog",
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
            )
        }
    }
}