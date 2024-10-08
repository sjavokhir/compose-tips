package com.compose.tips.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MultipleAvatarsContent() {
    Row(
        horizontalArrangement = Arrangement.spacedBy((-16).dp)
    ) {
//        users.forEachIndexed { index, user ->
//            GlideImage(
//                modifier = Modifier
//                    .size(52.dp)
//                    .zIndex((users.size - index).toFloat())
//                    .clip(CircleShape)
//                    .background(Color.Green)
//                    .border(width = 1.dp, color = Color.White, shape = CircleShape),
//                imageModel = { user.image }
//            )
//        }
    }
}