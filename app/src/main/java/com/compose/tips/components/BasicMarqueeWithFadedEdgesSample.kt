package com.compose.tips.components

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicMarqueeWithFadedEdgesSample() {
    val edgeWidth = 32.dp

    fun ContentDrawScope.drawFadedEdge(leftEdge: Boolean) {
        val edgeWidthPx = edgeWidth.toPx()
        drawRect(
            topLeft = Offset(if (leftEdge) 0f else size.width - edgeWidthPx, 0f),
            size = Size(edgeWidthPx, size.height),
            brush = Brush.horizontalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startX = if (leftEdge) 0f else size.width,
                endX = if (leftEdge) edgeWidthPx else size.width - edgeWidthPx
            ),
            blendMode = BlendMode.DstIn
        )
    }

    Text(
        text = "the quick brown fox jumped over the lazy dogs",
        fontSize = 32.sp,
        modifier = Modifier
            .widthIn(max = edgeWidth * 4)
            // Rendering to an offscreen buffer is required to get the faded edges' alpha to be
            // applied only to the text, and not whatever is drawn below this composable (e.g. the
            // window).
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent {
                drawContent()
                drawFadedEdge(leftEdge = true)
                drawFadedEdge(leftEdge = false)
            }
            .basicMarquee(
                // Animate forever.
                iterations = Int.MAX_VALUE,
                spacing = MarqueeSpacing(0.dp)
            )
            .padding(start = edgeWidth)
    )
}