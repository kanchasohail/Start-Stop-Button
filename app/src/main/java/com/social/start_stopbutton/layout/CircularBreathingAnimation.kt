package com.social.start_stopbutton.layout

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CircularBreathingAnimation(isServiceRunning: Boolean) {
    var breathing by remember { mutableStateOf(false) }

    LaunchedEffect(isServiceRunning) {
        breathing = true
        delay(1000)
        breathing = false
    }

    val alpha by animateFloatAsState(
        targetValue = if (breathing) 0.5f else 0.1f,
        animationSpec = tween(1000 , easing = LinearEasing),
        label = ""
    )

    val size by animateDpAsState(
        targetValue = if (breathing) 170.dp else 150.dp,
        animationSpec = tween(1000 , easing = LinearEasing),
        label = ""
    )

    val colors = if (!isServiceRunning) listOf(Color.Blue, Color.Cyan, Color.Green) else listOf(
        Color.Black, Color.Red
    )
    val brush = Brush.radialGradient(
        colors = colors,
        center = Offset.Unspecified,
        radius = size.value * 3
    )

    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .alpha(alpha)
            .background(brush),
    )
}
