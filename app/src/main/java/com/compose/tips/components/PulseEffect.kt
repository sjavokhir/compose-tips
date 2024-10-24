package com.compose.tips.components

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.scale

/**
 * Applies a double pulsating effect that is drawn behind the composable element. This effect
 * creates two consecutive pulsations, each with its own timing and easing, giving a layered
 * and dynamic visual appearance.
 *
 * @param targetScale The scale to which the pulse effect will grow during the animation.
 * @param initialScale The starting scale of the pulse effect.
 * @param color The color used to fill the pulse effect.
 * @param shape The shape of the pulse effect.
 * @param duration The total duration for the pulse animation in milliseconds.
 *
 * @return A [Modifier] that applies the double pulsating effect behind the composable element.
 */
@Composable
fun Modifier.doublePulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    color: Color = DefaultPulseColor,
    shape: Shape = CircleShape,
    duration: Int = 1200,
): Modifier = doublePulseEffect(targetScale, initialScale, SolidColor(color), shape, duration)

/**
 * Applies a double pulsating effect that is drawn behind the composable element. This effect
 * creates two consecutive pulsations, each with its own timing and easing, giving a layered
 * and dynamic visual appearance.
 *
 * @param targetScale The scale to which the pulse effect will grow during the animation.
 * @param initialScale The starting scale of the pulse effect.
 * @param brush The brush used to fill the pulse effect.
 * @param shape The shape of the pulse effect.
 * @param duration The total duration for the pulse animation in milliseconds.
 *
 * @return A [Modifier] that applies the double pulsating effect behind the composable element.
 */
@Composable
fun Modifier.doublePulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    brush: Brush = SolidColor(DefaultPulseColor),
    shape: Shape = CircleShape,
    duration: Int = 1200,
): Modifier {
    return this
        .pulseEffect(
            targetScale, initialScale, brush, shape,
            animationSpec = tween(duration, easing = FastOutSlowInEasing)
        )
        .pulseEffect(
            targetScale, initialScale, brush, shape,
            animationSpec = tween(
                durationMillis = (duration * 0.7f).toInt(),
                delayMillis = (duration * 0.3f).toInt(),
                easing = LinearEasing
            )
        )
}


/**
 * Applies a pulsating effect that is drawn behind the composable element. This effect creates
 * a visual appearance where the background pulse scales up and fades out in a loop, simulating
 * a pulsating effect.
 *
 * @param targetScale The scale to which the pulse effect will grow during the animation.
 * @param initialScale The starting scale of the pulse effect.
 * @param color The color used to fill the pulse effect.
 * @param shape The shape of the pulse effect.
 * @param animationSpec The animation specification.
 *
 * @return A [Modifier] that applies the pulsating effect behind the composable element.
 */
@Composable
fun Modifier.pulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    color: Color = DefaultPulseColor,
    shape: Shape = CircleShape,
    animationSpec: DurationBasedAnimationSpec<Float> = tween(1200)
): Modifier = pulseEffect(targetScale, initialScale, SolidColor(color), shape, animationSpec)

/**
 * Applies a pulsating effect that is drawn behind the composable element. This effect creates
 * a visual appearance where the background pulse scales up and fades out in a loop, simulating
 * a pulsating effect.
 *
 * @param targetScale The scale to which the pulse effect will grow during the animation.
 * @param initialScale The starting scale of the pulse effect.
 * @param brush The brush used to fill the pulse effect.
 * @param shape The shape of the pulse effect.
 * @param animationSpec The animation specification.
 *
 * @return A [Modifier] that applies the pulsating effect behind the composable element.
 */
@Composable
fun Modifier.pulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    brush: Brush = SolidColor(DefaultPulseColor),
    shape: Shape = CircleShape,
    animationSpec: DurationBasedAnimationSpec<Float> = tween(1200)
): Modifier {
    val pulseTransition = rememberInfiniteTransition("PulseTransition")
    val pulseScale by pulseTransition.animateFloat(
        initialValue = initialScale,
        targetValue = targetScale,
        animationSpec = infiniteRepeatable(animationSpec),
        label = "PulseScale"
    )
    val pulseAlpha by pulseTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(animationSpec),
        label = "PulseAlpha"
    )
    return this.drawBehind {
        val outline = shape.createOutline(size, layoutDirection, this)
        scale(pulseScale) {
            drawOutline(outline, brush, pulseAlpha)
        }
    }
}

private val DefaultPulseColor = Color.Black.copy(0.32f)