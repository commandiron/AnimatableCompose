package com.commandiron.animatablecompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.commandiron.animatable_compose.state.AnimatableCard
import com.commandiron.animatable_compose.state.AnimatableText
import com.commandiron.animatable_compose.state.rememberAnimatableSharedState
import kotlinx.coroutines.launch

@Composable
fun Show4() {
    val animatableSharedState = rememberAnimatableSharedState(
        initialSize = DpSize(80.dp, 80.dp),
        targetSize = DpSize(Dp.Infinity, 100.dp),
        sizeAnimationSpec = tween(500, 1000),
        initialShape = RoundedCornerShape(32.dp),
        targetShape = RoundedCornerShape(16.dp),
        initialOffset = DpOffset(0.dp, 0.dp),
        targetOffset = DpOffset(0.dp, - Dp.Infinity),
        offsetAnimationSpec = tween(1000),
        initialFontSize = 24.sp,
        targetFontSize = 48.sp,
        fontSizeAnimationSpec = tween(500, 1000),
    )
    val scope = rememberCoroutineScope()
    AnimatableCard(
        onClick = {
            scope.launch {
                animatableSharedState.transform()
            }
        },
        state = animatableSharedState
    ) {
        Box(Modifier.fillMaxSize(), Alignment.CenterStart) {
            AnimatableText(
                text = "Test",
                state = animatableSharedState
            )
        }
    }
}