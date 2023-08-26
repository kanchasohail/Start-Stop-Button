package com.social.start_stopbutton.layout

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun StartStopButton(isServiceRunning: Boolean, onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }
    val buttonSize by animateDpAsState(
        targetValue = if (isClicked) 140.dp else 150.dp,
        label = "buttonSize"
    )
    val textSize by animateIntAsState(
        targetValue = if (isClicked) 20 else 22,
        label = "textSize"
    )
    LaunchedEffect(key1 = isClicked) {
        delay(300)
        isClicked = false
    }
    Box(Modifier.size(170.dp), contentAlignment = Alignment.Center) {
        CircularBreathingAnimation(isServiceRunning)
        Box(
            modifier = Modifier
//            .size(150.dp)
                .size(buttonSize)
                .clip(CircleShape)
                .background(
                    if (isServiceRunning) Brush.linearGradient(
                        listOf(
                            Color.Red,
                            Color.Black
                        )
                    ) else Brush.linearGradient(
                        listOf(
                            Color.Blue,
                            Color.Cyan
                        )
                    )
                )
                .clickable {
                    isClicked = true
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (!isServiceRunning) "Start" else "Stop",
                fontSize = textSize.sp,
                color = Color.White,
            )
        }
    }
}